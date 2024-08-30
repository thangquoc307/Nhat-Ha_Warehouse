let itemOfPage = 5;
let pageLazyLoad = 0;
let pagePageable = 0;
let urlApi = "http://localhost:8080/api/1.0/";

let feedDataLazy = (id) => {
    let isFeed = checkLazyload(id);
    if (isFeed && pageLazyLoad != null) {
        $(function () {
            let customerId = $("#customer-select").val();
            $.ajax({
                type: "GET",
                url: `${urlApi}manage/service?id=${customerId}&page=${pageLazyLoad}&size=${itemOfPage}`,
                success: (result, status, xhr) => {
                    if (xhr.status === 200) {
                        $(`#${id}`).append(result.content.reduce((prev, cur, index) => {
                            return prev + `<div class="content-item">
                            <h5>${"🎯 " + cur.customerId + "-" + cur.name}</h5>
                            <p>- Service: ${cur.serviceName}</p>
                            <p>- Item: ${cur.count + " " + cur.unit}</p>
                            <p>- Price: ${cur.count} x ${cur.price} = ${roundToThreeDecimalPlaces(cur.count * cur.price)}💲</p>
                        </div>`;
                        }, ""))
                        pageLazyLoad++;

                        if (result.last) {
                            $(`#${id}`).append(`<img id="finish-img"
                        src="/resources/alert_image/Completed-bro.svg">`);
                            pageLazyLoad = null;
                        }
                    } else {
                        $(`#${id}`).html(
                            `<img id="no-content-img"
                        src="/resources/alert_image/204.svg">`);
                    }
                },
            })
        })
    }
}
let changeCustomer = () => {
    let customerId = $("#customer-select").val();
    if (customerId === "") {
        $("#display-service-detail")
            .html(`<img src="/resources/alert_image/204.svg">`)
        $("#total-money").text("0.00");
    } else {
        pageLazyLoad = 0;
        $("#display-service-detail").empty();
        feedDataLazy("display-service-detail");
        getTotal(customerId);
    }
}
let pageableLoad = (page) => {
    $(function () {
        $.ajax({
            type: "GET",
            url: `${urlApi}manage/device?page=${page}&size=${itemOfPage}`,
            success: (result, status, xhr) => {
                if (xhr.status === 200) {
                    let arrayData = result.content;
                    let totalPage = result.totalPages;
                    pagePageable = result.number;

                    $("#table-content").html(
                        arrayData.reduce((prev, cur, index) => {
                            return prev + `<tr>
                                <td>${1 + index + page * itemOfPage}</td>
                                <td>${cur.statusName == "available" 
                                    ? "✅ Available" : 
                                    (cur.statusName == "busy" 
                                    ? "⛔️ Busy" : "⚠️ Maintainance")}</td>
                                <td>${cur.customerId}</td>
                                <td>${cur.name}</td>
                                <td>${cur.deviceId}</td>
                                <td>${cur.position}</td>
                                <td>${coverDate(cur.useDate)}</td>
                                <td>${addHourSymbol(cur.useTime)}</td>
                                <td>${coverTimeDuration(cur.duration)}</td>
                            </tr>`;
                        },"")
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
                    $("#table-content").html(`<tr>
                         <td colspan="9" id="no-content-alert">
                            <img src="/resources/alert_image/204_v2.svg"> 
                        </td>
                    </tr>`)
                }
            },
        })
    });
}
let getTotal = (customerId) => {
    $.ajax({
        type: "GET",
        url: `${urlApi}manage/total/${customerId}`,
        success: (result, status, xhr) => {
            if (xhr.status === 200) {
                $("#total-money").text(roundToThreeDecimalPlaces(result));
            } else {
                $("#total-money").text("0.00");
            }
        }
    })
}

pageableLoad(0);