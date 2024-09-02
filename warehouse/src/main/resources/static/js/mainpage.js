let itemOfPage = 12;
let pagePageable = 0;
let urlApi = "http://localhost:8080/api/1.0/";
let stockChoosedId = 0;

let pageableLoad = (page) => {
    $(function () {
        let warehouseId = $("#warehouse-select").val();
        let startDate = $("#start-date-input").val();
        let endDate = $("#end-date-input").val();
        let searchInput = $("#search-input").val();

        $.ajax({
            type: "GET",
            url: `${urlApi}warehouse/show`
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
                                    + (cur.id == stockChoosedId ? "active-icon'" : "'") 
                                    + "onclick='showDetail(" + cur.id + ")'>start</div>"
                                    + cur.so
                                    + "</div></td>" : ""}
                                ${isNewStock ? "<td class='table-partner text-ellipsis' title='" + cur.partner 
                                    + "' rowspan='" + countId + "'>" + cur.partner + "</td>" : ""}
                                <td class="table-partnumber text-ellipsis"
                                 title="${cur.partNumber}">${cur.partNumber}</td>
                                <td class="table-description text-ellipsis"
                                 title="${cur.description}">${cur.description}</td>
                                <td class="table-count text-center">${cur.count}</td>
                                
                                ${isNewStock ? "<td class='table-saler text-ellipsis' title='" + (cur.name ?? "")
                                    + "' rowspan='" + countId + "'>" + (cur.name ?? "") + "</td>" : ""}
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
    });
}
let renderSerial = (arrayItem) => {
    let data = arrayItem.reduce((prev, cur, index) => {
        return prev + `<div>
                            <div>${index + 1} - ${cur.serial}</div>
                            <span class="material-symbols-outlined">edit</span>
                            <span class="material-symbols-outlined">delete</span>
                       </div>`
    }, `<div class="serial-list-detail">`);
    return data + "</div>";
}
let showDetail = (stockId) => {
    $(function () {
        $("#start-button-" + stockChoosedId).removeClass("active-icon");
        $("#start-button-" + stockId).addClass("active-icon");
        if (stockId) {
            stockChoosedId = stockId;
        } else {
            stockId = stockChoosedId;
        }
        $.ajax({
            type: "GET",
            url: `${urlApi}warehouse/item-detail?stockId=${stockId}`,
            success: (result, status, xhr) => {
                if (xhr.status === 200) {
                    let arrayData = result;
                    $("#line-detail").html(
                        arrayData.reduce((prev, cur) => {
                            return prev + `<div class="item-list-detail mb-3">
                                <h5>${cur.partNumber}</h5>
                                <div class="line-detail-button d-flex flex-row-reverse gap-1">
                                    <span class="material-symbols-outlined">delete</span>
                                    <span class="material-symbols-outlined">edit</span>
                                    <span class="material-symbols-outlined">inventory_2</span>
                                </div>
                                <p class="m-1">👉 <span class="fw-bold">Số lượng: </span>${cur.count} 
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
let searchingData = () => {
    debouncing(() => {
        pageableLoad(0);
    })
}
pageableLoad(0);
// showDetail(1);