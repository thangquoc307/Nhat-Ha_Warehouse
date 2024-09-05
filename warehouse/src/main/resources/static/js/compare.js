let workbook;
let worksheet;
let endRow = 0;
let arrayData = [];
let urlApi = "http://localhost:8080/api/1.0/";

class DataExcel {
    partNumber;
    count;
    constructor(partNumber, count) {
        this.partNumber = partNumber;
        this.count = count;
    }
}

let changeFile = () => {
    $(function () {
        let input = document.getElementById("excel-file");
        let file = input.files[0];
        if (file) {
            let reader = new FileReader();
            reader.onload = function (e) {
                let data = new Uint8Array(e.target.result);
                workbook = XLSX.read(data, { type: "array" });

                $("#select-sheet").html(
                    workbook.SheetNames.reduce((prev, cur, index) => {
                        return prev + ` <option value="${cur}">${cur}</option>`;
                }, "<option selected value=''>-- Select Sheet --</option>"));
            };
            reader.readAsArrayBuffer(file);
        }
    })
}
let changeSheet = () => {
    $(function () {
        let indexSheet = $("#select-sheet").val();
        let setColumn = new Set();
        if (indexSheet != '') {
            worksheet = workbook.Sheets[indexSheet];
            for (let worksheetKey in worksheet) {
                let match = worksheetKey.match(/^([A-Z]+)(\d+)$/i);

                if (match) {
                    let letters = match[1];
                    let numbers = +match[2];

                    setColumn.add(letters);
                    if (endRow < numbers) {
                        endRow = numbers;
                    }
                }
            }
            let sortedArray = Array.from(setColumn).sort((a, b) => a - b);
            let data = sortedArray.reduce((prev, cur) => {
                return prev + `<option>${cur}</option>`;
            }, "");
            $("#select-part").html(
                `<option value="" selected>-- Part Number --</option>` + data);
            $("#select-quantity").html(
                `<option value="" selected>-- Quantity --</option>` + data);
        }
    })

}
let changeColumn = () => {
    $(function () {
        let partValue = $("#select-part").val();
        let quantityVal = $("#select-quantity").val();
        arrayData = [];

        if (partValue != "" && quantityVal != "") {
            for (let i = 0; i <= endRow; i++) {
                let partData = worksheet[partValue + i];
                let countData = worksheet[quantityVal + i];
                if (partData && partData.v && typeof partData.v === 'string' &&
                    countData && countData.v && !isNaN(countData.v)) {
                    arrayData.push(new DataExcel(partData.v, countData.v));
                }
            }
        }
    });
}
let countItem = (detailItemArray) => {
    return detailItemArray.reduce((prev, cur) => {return prev + cur.countData}, 0)
}
let setupDetail = (detailItemArray) => {
    if (detailItemArray.length == 0) {
        return "<td></td>"
    } else {
        let data = detailItemArray.reduce((prev, cur) => {
            return prev + `<tr><td>${coverDate(cur.releaseDate)} - SO: ${cur.so} - SL: ${cur.countData}</td></tr>`
        }, "");
        console.log(data.substring(4, data.length - 4));
        return data.substring(4, data.length - 5);
    }
}
let setupData = () => {
    $(function () {
        let warehouseId = $("#warehouse-select").val();
        let startDate = $("#start-date-input").val();
        let endDate = $("#end-date-input").val();
        $.ajax({
            url: `${urlApi}compare/data?warehouseId=${warehouseId}`
                + `&startDate=${startDate}&endDate=${endDate}`,
            type: "GET",
            success: (result, status, xhr) => {
                $("#data-compare").html(
                    result.reduce((prev, cur, index) => {
                        let row =
                            cur.detailItem.length == 0 ? 1 : cur.detailItem.length;
                        let count = countItem(cur.detailItem);
                        return prev + `<tr>
                            <td class="text-center" rowspan="${row}">${index + 1}</td>
                            <td rowspan="${row}">${cur.partNumber}</td>
                            <td class="text-center" rowspan="${row}">${cur.countInput ?? "(Không có Data)"}</td>
                            <td class="text-center" rowspan="${row}">${count == 0 ? "(Không có Data)" : count}</td>
                            <th class="text-danger text-center" rowspan="${row}">${
                                (count != 0 && cur.countInput != 0) ? cur.countInput - count : ""}</th>
                            ${setupDetail(cur.detailItem)}
                        </tr>`;
                    }, "")
                );
            }
        })
    })
}
let showConfirmModal = () => {
    $(function () {
        closeModal();
        $("body").append(`<div id="show-modal" >
                <div></div>
                <div class="boxshadow-outset borderradius color0">
                    <h4 class="mb-2">Review Data</h4>
                    <div class="display-table scroll-custom"><table class="table table-striped">
                    <thead><tr>
                        <th>No</th>
                        <th>Part Number</th>
                        <th>Quantity</th>
                    </tr></thead>
                    <tbody>
                        ${arrayData.reduce((prev, cur, index) => {
                            return prev + `<tr>
                                <td>${index}</td>
                                <td>${cur.partNumber}</td>
                                <td>${cur.count}</td>
                            </tr>`          
                        }, "")}
                    </tbody>
                    </table></div>
                    <div class="d-flex flex-row-reverse gap-2">
                        <button onclick="sendData()">
                            <span class="material-symbols-outlined">send</span>
                        </button>     
                        <button onclick="closeShowModal()">
                            <span class="material-symbols-outlined">close</span>
                        </button>
                    </div>
                </div>
            </div>`);
    });
}
let closeShowModal = () => {
    $(`#show-modal`).fadeOut(500, function() {
        $(this).remove();
    });
}
let sendData = () => {
    $(function () {
        if (arrayData.length > 0) {
            $.ajax({
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(arrayData),
                url: `${urlApi}compare/send`,
                success: () => {
                    setupData();
                    closeModal();
                }
            })
        }
    })
}
setupData();
