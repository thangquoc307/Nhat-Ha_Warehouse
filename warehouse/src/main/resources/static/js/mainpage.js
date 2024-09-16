let isInbound = false;
let itemOfPage = 12;
let pagePageable = 0;
let urlApi = "http://localhost:8080/api/1.0/";
let outboundChoosedId = 0;
let inboundChoosedId = 0;
let outboundChoosedName = "";
let inboundChoosedName = "";
let listSerial = [];
let idSerialEdited = 0;
let isInboundSerial = false;
class SerialItem {
    id;
    name;
    index;

    constructor(id, name, index) {
        this.id = id;
        this.name = name;
        this.index = index;
    }
}
let pageableLoad = (page) => {
    $(function () {
        let warehouseId = $("#warehouse-select").val();
        let startDate = $("#start-date-input").val();
        let endDate = $("#end-date-input").val();
        let searchInput = $("#search-input").val();
        isInbound = $("#input").prop('checked');

        if (isInbound) {
            $.ajax({
                type: "GET",
                url: `${urlApi}warehouse/show-inbound`
                    + `?search=${searchInput}&warehouseId=${warehouseId}`
                    + `&startDate=${startDate}&endDate=${endDate}`
                    + `&page=${page}&size=${itemOfPage}`,
                success: (result, status, xhr) => {
                    if (xhr.status === 200) {
                        let arrayData = result.content;
                        let totalPage = result.totalPages;
                        pagePageable = result.number;

                        let curMonth = "";
                        let monthArray = arrayData.map(value => getMonth(value.releaseDate));

                        let curDate = "";
                        let dateArray = arrayData.map(value => value.releaseDate);

                        let curId = "";
                        let idArray = arrayData.map(value => value.id);

                        $("#stock-title").html(`<tr>
                            <th colspan="2">Ngày xuất</th>
                            <th>Mã nhập kho</th>
                            <th>Hãng</th>
                            <th>Mã hàng</th>
                            <th>Thiết bị</th>
                            <th>SL</th>
                            <th>Nơi gửi</th>
                            <th>File</th>
                            <th>Ghi chú</th>
                        </tr>`)

                        $("#stock-data").html(
                            arrayData.reduce((prev, cur, index) => {
                                let strMonth = getMonth(cur.releaseDate);
                                let isNewMonth = strMonth != curMonth
                                let countRow;
                                if (isNewMonth) {
                                    curMonth = strMonth;
                                    countRow = countInArray(monthArray, strMonth);
                                }

                                let isNewDate = cur.releaseDate != curDate
                                let countDate;
                                if (isNewDate) {
                                    curDate = cur.releaseDate;
                                    countDate = countInArray(dateArray, curDate);
                                }

                                let isNewStock = cur.id != curId;
                                let countId;
                                if (isNewStock) {
                                    curId = cur.id;
                                    countId = countInArray(idArray, curId);
                                }

                                return prev + `<tr>
                                ${isNewMonth ? "<td class='table-month text-center' rowspan='"
                                    + countRow + "'>" + curMonth + "</td>" : ""}
                                ${isNewDate ? "<td class='table-date text-center' rowspan='"
                                    + countDate + "'>" + coverDate(cur.releaseDate) + "</td>" : ""}
                                ${isNewStock ? "<td class='table-so text-ellipsis' rowspan='"
                                    + countId + "'><div>"
                                    + "<div id='start-button-" + cur.id
                                    + "' class='material-symbols-outlined "
                                    + (cur.id == inboundChoosedId ? "active-icon'" : "'")
                                    + "onclick='showDetail(" + cur.id + ", \"" + cur.inboundCode + "\")'>start</div>"
                                    + (cur.inboundCode ?? "")
                                    + "</div></td>" : ""}
                                <td class="table-manufacturer text-ellipsis"
                                 title="${cur.name ?? ""}">${cur.name ?? ""}</td>
                                <td class="table-partnumber text-ellipsis"
                                 title="${cur.partNumber}">${cur.partNumber ?? "Chưa có thông tin"}</td>
                                <td class="table-description text-ellipsis"
                                 title="${cur.description}">${cur.description ?? "Chưa có thông tin"}</td>
                                <td class="table-count text-center">${cur.count}</td>
                                
                                ${isNewStock ? "<td class='table-saler text-ellipsis' title='" + (cur.locationFrom ?? "")
                                    + "' rowspan='" + countId + "'>" + (cur.locationFrom ?? "") + "</td>" : ""}
                                ${isNewStock ? "<td class='table-file' "
                                    + "rowspan='" + countId + "'><div class='d-flex flex-row gap-2 justify-content-center align-items-center'>"
                                    + `<span class="material-symbols-outlined" onclick='uploadPdf(${cur.id})'>upload_file</span>`
                                    + (cur.image == null
                                            ? `<span class="material-symbols-outlined disable-button">image</span>`
                                            : `<span class="material-symbols-outlined" onclick="showPdf(${cur.id}, true)">image</span>`
                                    ) + "</div></td>" : ""}
                                ${isNewStock ? "<td class='table-note text-ellipsis' title='" + cur.note
                                    + "' rowspan='" + countId + "'>" + cur.note + "</td>" : ""}
                            </tr>`;
                            }, "")
                        )
                        $("#content-table-pageable").html(
                            (pagePageable > 0 ?
                                `<div class="material-symbols-outlined boundarybutton-active"
                        onclick="pageableLoad(${pagePageable - 1})">stat_2</div>`
                                : `<div class="material-symbols-outlined boundarybutton-inactive"
                        >do_not_disturb_on</div>`) +

                            (pagePageable > 1 && totalPage - pagePageable <= 1 ?
                                `<div onclick="pageableLoad(${pagePageable - 2})">
                        ${pagePageable - 1}</div>` : "") +

                            (pagePageable > 0 ?
                                `<div onclick="pageableLoad(${pagePageable - 1})">
                        ${pagePageable}</div>` : "") +

                            `<div class="current-page">${pagePageable + 1}</div>` +

                            (totalPage - pagePageable > 1 ?
                                `<div onclick="pageableLoad(${pagePageable + 1})">
                        ${pagePageable + 2}</div>` : "") +

                            (totalPage - pagePageable > 2 && pagePageable < 1 ?
                                `<div onclick="pageableLoad(${pagePageable + 2})">
                        ${pagePageable + 3}</div>` : "") +

                            (totalPage - pagePageable > 1 ?
                                `<div class="material-symbols-outlined boundarybutton-active"
                        onclick="pageableLoad(${pagePageable + 1})">stat_minus_2</div>`
                                : `<div class="material-symbols-outlined boundarybutton-inactive"
                        >do_not_disturb_on</div>`)
                        )
                    } else {
                        $("#content-table-pageable").empty();
                        $("#stock-data").html(`<tr>
                         <td colspan="10" id="no-content-alert">
                            <img src="/alert_image/204_v2.svg">
                        </td>
                    </tr>`)
                    }
                },
            })
        } else {
            $.ajax({
                type: "GET",
                url: `${urlApi}warehouse/show-outbound`
                    + `?search=${searchInput}&warehouseId=${warehouseId}`
                    + `&startDate=${startDate}&endDate=${endDate}`
                    + `&page=${page}&size=${itemOfPage}`,
                success: (result, status, xhr) => {
                    if (xhr.status === 200) {
                        let arrayData = result.content;
                        let totalPage = result.totalPages;
                        pagePageable = result.number;

                        let curMonth = "";
                        let monthArray = arrayData.map(value => getMonth(value.releaseDate));

                        let curDate = "";
                        let dateArray = arrayData.map(value => value.releaseDate);

                        let curId = "";
                        let idArray = arrayData.map(value => value.id);

                        $("#stock-title").html(`<tr>
                        <th colspan="2">Ngày xuất</th>
                        <th>Số SO</th>
                        <th>Partner</th>
                        <th>Hãng</th>
                        <th>Mã hàng</th>
                        <th>Thiết bị</th>
                        <th>SL</th>
                        <th>Saler</th>
                        <th>File</th>
                        <th>Ghi chú</th>
                    </tr>`)

                        $("#stock-data").html(
                            arrayData.reduce((prev, cur, index) => {
                                let strMonth = getMonth(cur.releaseDate);
                                let isNewMonth = strMonth != curMonth
                                let countRow;
                                if (isNewMonth) {
                                    curMonth = strMonth;
                                    countRow = countInArray(monthArray, strMonth);
                                }

                                let isNewDate = cur.releaseDate != curDate
                                let countDate;
                                if (isNewDate) {
                                    curDate = cur.releaseDate;
                                    countDate = countInArray(dateArray, curDate);
                                }

                                let isNewStock = cur.id != curId;
                                let countId;
                                if (isNewStock) {
                                    curId = cur.id;
                                    countId = countInArray(idArray, curId);
                                }

                                return prev + `<tr>
                                ${isNewMonth ? "<td class='table-month text-center' rowspan='"
                                    + countRow + "'>" + curMonth + "</td>" : ""}
                                ${isNewDate ? "<td class='table-date text-center' rowspan='"
                                    + countDate + "'>" + coverDate(cur.releaseDate) + "</td>" : ""}
                                ${isNewStock ? "<td class='table-so' rowspan='"
                                    + countId + "'><div>"
                                    + "<div id='start-button-" + cur.id
                                    + "' class='material-symbols-outlined "
                                    + (cur.id == outboundChoosedId ? "active-icon'" : "'")
                                    + "onclick='showDetail(" + cur.id + ", \"" + cur.so + "\")'>start</div>"
                                    + (cur.so ?? "")
                                    + "</div></td>" : ""}
                                ${isNewStock ? "<td class='table-partner text-ellipsis' title='" + cur.partner
                                    + "' rowspan='" + countId + "'>" + (cur.partner ?? "") + "</td>" : ""}
                                <td class="table-manufacturer text-ellipsis"
                                 title="${cur.manufacturer ?? ""}">${cur.manufacturer ?? ""}</td>
                                <td class="table-partnumber text-ellipsis"
                                 title="${cur.partNumber}">${cur.partNumber ?? "Chưa có thông tin"}</td>
                                <td class="table-description text-ellipsis"
                                 title="${cur.description}">${cur.description ?? "Chưa có thông tin"}</td>
                                <td class="table-count text-center">${cur.count}</td>
                                
                                ${isNewStock ? "<td class='table-saler text-ellipsis' title='" + (cur.name ?? "")
                                    + "' rowspan='" + countId + "'>" + (cur.name ?? "") + "</td>" : ""}
                                ${isNewStock ? "<td class='table-file' "
                                    + "rowspan='" + countId + "'><div class='d-flex flex-row gap-2 justify-content-center align-items-center'>"
                                    + `<span class="material-symbols-outlined" onclick='uploadPdf(${cur.id})'>upload_file</span>`
                                    + (cur.image == null
                                            ? `<span class="material-symbols-outlined disable-button">image</span>`
                                            : `<span class="material-symbols-outlined" onclick="showPdf(${cur.id}, false)">image</span>`
                                    ) + "</div></td>" : ""}
                                ${isNewStock ? "<td class='table-note text-ellipsis' title='" + cur.note
                                    + "' rowspan='" + countId + "'>" + cur.note + "</td>" : ""}
                            </tr>`;
                            }, "")
                        )
                        $("#content-table-pageable").html(
                            (pagePageable > 0 ?
                                `<div class="material-symbols-outlined boundarybutton-active"
                        onclick="pageableLoad(${pagePageable - 1})">stat_2</div>`
                                : `<div class="material-symbols-outlined boundarybutton-inactive"
                        >do_not_disturb_on</div>`) +

                            (pagePageable > 1 && totalPage - pagePageable <= 1 ?
                                `<div onclick="pageableLoad(${pagePageable - 2})">
                        ${pagePageable - 1}</div>` : "") +

                            (pagePageable > 0 ?
                                `<div onclick="pageableLoad(${pagePageable - 1})">
                        ${pagePageable}</div>` : "") +

                            `<div class="current-page">${pagePageable + 1}</div>` +

                            (totalPage - pagePageable > 1 ?
                                `<div onclick="pageableLoad(${pagePageable + 1})">
                        ${pagePageable + 2}</div>` : "") +

                            (totalPage - pagePageable > 2 && pagePageable < 1 ?
                                `<div onclick="pageableLoad(${pagePageable + 2})">
                        ${pagePageable + 3}</div>` : "") +

                            (totalPage - pagePageable > 1 ?
                                `<div class="material-symbols-outlined boundarybutton-active"
                        onclick="pageableLoad(${pagePageable + 1})">stat_minus_2</div>`
                                : `<div class="material-symbols-outlined boundarybutton-inactive"
                        >do_not_disturb_on</div>`)
                        )
                    } else {
                        $("#content-table-pageable").empty();
                        $("#stock-data").html(`<tr>
                         <td colspan="10" id="no-content-alert">
                            <img src="/alert_image/204_v2.svg">
                        </td>
                    </tr>`)
                    }
                },
            })
        }
    });
}
let renderSerial = (arrayItem) => {
    let data = arrayItem.reduce((prev, cur, index) => {
        listSerial.push(new SerialItem(cur.id, cur.serial, index))
        return prev + `<div id="edit-serial-${cur.id}">
                            <div>${index + 1} - ${cur.serial}</div>
                            <span class="material-symbols-outlined"
                                onclick="showEditSerial(${cur.id})">edit</span>
                            <span class="material-symbols-outlined"
                                onclick="showDeleteModal(
                                    '${cur.id}', '${cur.serial}', '${isInbound ? mode.INBOUND_SERIAL : mode.OUTBOUND_SERIAL}')"
                                    >delete</span>
                       </div>`
    }, `<div class="serial-list-detail">`);
    return data + "</div>";
}
let showDetail = (choosedId, name) => {
    $(function () {
        $("#start-button-" + (isInbound ? inboundChoosedId : outboundChoosedId)).removeClass("active-icon");
        if (isInbound) {
            outboundChoosedId = 0
        } else {
            inboundChoosedId = 0;
        }

        if (isInbound) {
            if (choosedId) {
                inboundChoosedId = choosedId;
                inboundChoosedName = name;
            } else {
                choosedId = inboundChoosedId;
                name = inboundChoosedName;
            }
        } else {
            if (choosedId) {
                outboundChoosedId = choosedId;
                outboundChoosedName = name;
            } else {
                choosedId = outboundChoosedId;
                name = outboundChoosedName;
            }
        }
        isInboundSerial = isInbound;
        $("#start-button-" + choosedId).addClass("active-icon");
        $.ajax({
            type: "GET",
            url: `${urlApi}warehouse/${isInbound ? "inbound" : "outbound"}-item-detail?${isInbound ? "inboundId" : "outboundId"}=${choosedId}`,
            success: (result, status, xhr) => {
                if (xhr.status === 200) {
                    listSerial = [];
                    idSerialEdited = 0;
                    let arrayData = result;
                    $("#line-detail").html(
                        arrayData.reduce((prev, cur) => {
                            return prev + `<div class="item-list-detail mb-3">
                                <h5>${cur.partNumber}</h5>
                                <div class="line-detail-button d-flex flex-row-reverse gap-1">
                                    <span class="material-symbols-outlined"
                                        onclick="showDeleteModal('${cur.id}', '${cur.partNumber}',
                                         '${isInbound ? mode.INBOUND_ITEM : mode.OUTBOUND_ITEM}')">delete</span>
                                    <span class="material-symbols-outlined"
                                        onclick="gotoModifyItem(${cur.id})">edit</span>
                                    ${cur.hasSerial ?
                                    `<span class="material-symbols-outlined"
                                        onclick="showTableCreateSerial(${cur.id}, '${cur.partNumber}')">
                                        inventory_2</span>`
                                    : ``}
                                </div>
                                <p class="m-1 d-flex gap-2 align-items-center">👉 <span class="fw-bold">Số lượng: </span>${cur.count} 
                                    ${cur.hasSerial ? `` : `<span class="button-edit-count material-symbols-outlined"
                                    onclick="showEditCount(${cur.id}, ${cur.count}, '${cur.partNumber}')"
                                    >edit_square</span>`}</p>
                                <p class="m-1">👉 <span class="fw-bold">Thiết bị: </span>${cur.description}</p>
                                ${(cur.itemSerials.length > 0 ? renderSerial(cur.itemSerials) : "")}
                            </div>`;
                        }, "")
                    )
                    $("#line-detail").append(`<img src="/alert_image/Completed-bro.svg">`)
                } else {
                    $("#line-detail").html(`<img src="/alert_image/204.svg">`)
                }
            },
        })
    })
}
let resetDetail = () => {
    $(function () {
        $("#line-detail").html(`<img src="/alert_image/204.svg">`);
        outboundChoosedId = 0;
        outboundChoosedName = "";
    })
}
let searchingData = () => {
    debouncing(() => {
        pageableLoad(0);
    })
}
let showEditSerial = async (id) => {
    await closeEditSerial(idSerialEdited);
    idSerialEdited = id;
    $(function () {
        let data = listSerial.find(value => value.id == id);
        $(`#edit-serial-${id}`).html(`<input class="form-control" 
            type="text" value="${data.name}">
            <span class="material-symbols-outlined" 
                onclick="closeEditSerial(${data.id})">close</span>
            <span class="material-symbols-outlined"
                onclick="sendEditSerial(${data.id})">check</span>`)
    })
}
let closeEditSerial = (id) => {
    $(function () {
        let data = listSerial.find(value => value.id == id);
        $(`#edit-serial-${id}`).html(`<div>${data.index + 1} - ${data.name}</div>
            <span class="material-symbols-outlined"
                onclick="showEditSerial(${data.id})">edit</span>
            <span class="material-symbols-outlined"
                onclick="showDeleteModal(
                    '${data.id}', '${data.name}', '${isInbound ? mode.INBOUND_SERIAL : mode.OUTBOUND_SERIAL}')"
                    >delete</span>`)
    })
}
let sendEditSerial = (id) => {
    $(function () {
        let value = $(`#edit-serial-${id} input`).val();
        $.ajax({
            type: "PUT",
            url: `${urlApi}data/serial`,
            contentType: "application/json",
            data: JSON.stringify({id: id, name: value, isInbound: isInboundSerial}),
            success: () => {
                showDetail(outboundChoosedId, outboundChoosedName);
            }
        })
    })
}
let showEditCount = (id, count, name) => {
    $(function () {
        closeEditCount();
        $("body").append(`<div id="edit-count">
            <div></div>
            <div class="color0 borderradius boxshadow-outset p-3">
                <h4>Edit ${name} Quantity</h4>
                <div class="input-group flex-nowrap">
                    <span class="input-group-text material-symbols-outlined">
                    account_balance_wallet</span>
                    <input id="edit-count-val" type="number" 
                    class="form-control" min="0" value="${count}">
                    <input id="edit-count-id" hidden="hidden" value="${id}">
                </div>
                <p id="edit-count-alert" hidden="hidden" 
                    class="textAlert text-end">Chỉ nhập số lớn hơn 0</p>
                <div class="d-flex flex-row-reverse gap-2 mt-3">
                    <button onclick="sendEditCount()">
                        <span class="material-symbols-outlined">send</span> Send
                    </button>
                    <button onclick="closeEditCount()">
                        <span class="material-symbols-outlined">close</span> Cancel
                    </button>
                </div>
            </div>
        </div>`)
    })
}
let closeEditCount = () => {
    $(`#edit-count`).fadeOut(500, function() {
        $(this).remove();
    });
}
let sendEditCount = () => {
    $(function () {
        let id = $("#edit-count-id").val();
        let value = +$("#edit-count-val").val();
        if (value < 0) {
            $("#edit-count-alert").removeAttr("hidden");
        } else {
            $.ajax({
                type: "POST",
                url: `${urlApi}data/count`,
                contentType: "application/json",
                data: JSON.stringify({id: id, count: value, isInbound: isInboundSerial}),
                success: () => {
                    closeEditCount();
                    pageableLoad(pagePageable);
                    showDetail(null, "");
                }
            })
        }
    })
}
let gotoModifyStock = (isCreate) => {
    $(function () {
        let warehouseId = $("#warehouse-select").val();
        if ((outboundChoosedId !== 0 || inboundChoosedId !== 0) && !isCreate) {
            gotoLink(`/publish/${isInbound ? "inbound" : "outbound"}?id=${isInbound ? inboundChoosedId : outboundChoosedId}&warehouse-id=${warehouseId}`);
        } else if (isCreate) {
            gotoLink(`/publish/${isInbound ? "inbound" : "outbound"}?warehouse-id=${warehouseId}`);
        }
    })
}
let gotoModifyItem = (editId) => {
    $(function () {
        if (editId !== 0 && outboundChoosedId !== 0) {
            gotoLink(`/publish/item?id=${editId}&stock-id=${outboundChoosedId}`);
        } else if (outboundChoosedId !== 0) {
            gotoLink(`/publish/item?stock-id=${outboundChoosedId}`);
        }
    })
}
let uploadPdf = async (id) => {
    $("#upload-pdf").click();
    $("#upload-pdf").on("change", async function () {
        let fileInput = this.files[0];
        if (fileInput) {
            let formData = new FormData();
            formData.append("file", fileInput);
            try {
                let response = await $.ajax({
                    url: `${urlApi}warehouse/upload-pdf/${id}?isInbound=${isInbound}`,
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false
                });
                gotoLink("/publish/home");
            } catch (error) {
                console.error("Upload failed", error);
            }
        }
    });
}
let showPdf = (id, inbound) => {
    $(function () {
        $.ajax({
            url: `${urlApi}warehouse/pdf/${id}?isInbound=${inbound}`,
            type: 'GET',
            xhrFields: {
                responseType: 'blob'
            },
            success: function (blob) {
                let url = URL.createObjectURL(blob);
                window.open(url, '_blank');
            }
        });
    })
}
pageableLoad(0);
