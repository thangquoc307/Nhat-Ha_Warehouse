let url = "http://localhost:8080/api/1.0/";
let currentPage = 0;
let itemOfPageable = 5;
let arrayLink = ["device", "service", "customer"];
let currentMode = 0;

let switchModeTablePage = (mode) => {
    currentMode = mode
    changeHeader(mode);
    changeBody(mode, 0);
}
let changeBody = (mode, page) => {
    $(function () {
        let searchKey = $("#search-input").val();
        $.ajax({
            type: "GET",
            url: `${urlApi + arrayLink[mode]}/display?search=${searchKey}&page=${page}&size=${itemOfPageable}`,
            success: (result, status, xhr) => {
                if (xhr.status === 200) {
                    let arrayData = result.content;
                    let totalPage = result.totalPages;
                    currentPage = result.number;
                    let data = "";

                    switch (mode) {
                        case 0:
                            data = buildDevide(arrayData, page);
                            break;
                        case 1:
                            data = buildService(arrayData, page);
                            break;
                        case 2:
                            data = buildCustomer(arrayData, page);
                            break;
                    }

                    $("#page-table-body").html(data);
                    $("#page-table-pageable").html(
                        (currentPage > 0 ?
                            `<div class="material-symbols-outlined boundarybutton-active" 
                        onclick="changeBody(${currentMode}, ${currentPage - 1})">
                        arrow_back_ios</div>`
                            : `<div class="material-symbols-outlined boundarybutton-inactive" 
                        >arrow_back_ios</div>`) +

                        (currentPage > 1 && totalPage - currentPage <= 1 ?
                            `<div onclick="changeBody(${currentMode}, ${currentPage - 2})">
                        ${currentPage - 1}</div>` : "") +

                        (currentPage > 0 ?
                            `<div onclick="changeBody(${currentMode}, ${currentPage - 1})">
                        ${currentPage}</div>` : "") +

                        `<div class="current-page">${currentPage + 1}</div>` +

                        (totalPage - currentPage > 1 ?
                            `<div onclick="changeBody(${currentMode}, ${currentPage + 1})">
                        ${currentPage + 2}</div>` : "") +

                        (totalPage - currentPage > 2 && currentPage < 1 ?
                            `<div onclick="changeBody(${currentMode}, ${currentPage + 2})">
                        ${currentPage + 3}</div>` : "") +

                        (totalPage - currentPage > 1 ?
                            `<div class="material-symbols-outlined boundarybutton-active" 
                        onclick="changeBody(${currentMode}, ${currentPage + 1})">
                        arrow_forward_ios</div>`
                            : `<div class="material-symbols-outlined boundarybutton-inactive" 
                        >arrow_forward_ios</div>`)
                    )
                } else {
                    $("#page-table-pageable").empty();
                    $("#page-table-body").html(`<tr>
                         <td colspan="9" id="no-content-alert">
                            <img src="/resources/alert_image/204_v2.svg"> 
                        </td>
                    </tr>`)
                }
            },
        })
    });
}
let changeHeader = (mode) => {
    $(function () {
        $(".table-switch>div:nth-child(1)>button")
            .removeClass("table-switch-button-active");
        switch (mode) {
            case 0:
                $("#table-button-add span:nth-last-child(1)").text("Add Devide");
                $(".table-switch>div:nth-child(1)>button:nth-child(1)")
                    .addClass("table-switch-button-active");
                $("#page-table-title").text("Device manager");
                $("#page-table-header").html(`
                <tr>
                    <th>#</th>
                    <th>Devide Id</th>
                    <th>Position</th>
                    <th>Status</th>
                    <th></th>
                </tr>`)
                break;
            case 1:
                $("#table-button-add span:nth-last-child(1)").text("Add Service");
                $(".table-switch>div:nth-child(1)>button:nth-child(2)")
                    .addClass("table-switch-button-active");
                $("#page-table-title").text("Service manager");
                $("#page-table-header").html(`
                <tr>
                    <th>#</th>
                    <th>Service Id</th>
                    <th>Service Name</th>
                    <th>Unit</th>
                    <th>Price</th>
                    <th></th>
                </tr>`)
                break;
            case 2:
                $("#table-button-add span:nth-last-child(1)").text("Add Customer");
                $(".table-switch>div:nth-child(1)>button:nth-child(3)")
                    .addClass("table-switch-button-active");
                $("#page-table-title").text("Customer manager");
                $("#page-table-header").html(`
                <tr>
                    <th>#</th>
                    <th>Customer Id</th>
                    <th>Customer Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th></th>
                </tr>`)
                break;
        }
    })
}
let buildDevide = (arrayData, page) => {
    return arrayData.reduce((prev, cur, index) => {
        return prev + `<tr>
           <td>${1 + index + page * itemOfPageable}</td>   
           <td>${cur.deviceId}</td>   
           <td>${cur.position}</td>   
           <td>${cur.status}</td>  
           <td>
               <span class="material-symbols-outlined edit-button"
                   onclick="gotoLink('/device/create?id=${cur.deviceId}')"
                   >edit</span>
               <span class="material-symbols-outlined del-button"
                   onclick="showDeleteModal('${cur.deviceId}', '${cur.deviceId}')"
                   >delete</span>
           </td>
        </tr>`;
    },"");
}
let buildService = (arrayData, page) => {
    return arrayData.reduce((prev, cur, index) => {
        return prev + `<tr>
           <td>${1 + index + page * itemOfPageable}</td>   
           <td>${cur.serviceId}</td>   
           <td>${cur.name}</td>   
           <td>${cur.unit}</td>  
           <td>${cur.price}</td>  
           <td>
               <span class="material-symbols-outlined edit-button"
                   onclick="gotoLink('/service/create?id=${cur.serviceId}')"
                   >edit</span>
               <span class="material-symbols-outlined del-button"
                   onclick="showDeleteModal('${cur.serviceId}', '${cur.name}')"
                   >delete</span>
           </td>
        </tr>`;
    },"");
}
let buildCustomer = (arrayData, page) => {
    return arrayData.reduce((prev, cur, index) => {
        return prev + `<tr>
           <td>${1 + index + page * itemOfPageable}</td>   
           <td>${cur.customerId}</td>   
           <td>${cur.name}</td>   
           <td>${cur.phone}</td>  
           <td>${cur.email}</td>  
           <td>${cur.address}</td>  
           <td>
               <span class="material-symbols-outlined edit-button"
                   onclick="gotoLink('/customer/create?id=${cur.customerId}')"
                   >edit</span>
               <span class="material-symbols-outlined del-button"
                   onclick="showDeleteModal('${cur.customerId}', '${cur.name}')"
                   >delete</span>
           </td>
        </tr>`;
    },"");
}
let searchingData = () => {
    debouncing(() => {
        changeBody(currentMode, 0);
    })
}
let gotoCreatePage = () => {
    gotoLink(`/${arrayLink[currentMode]}/create`);
}

switchModeTablePage(0);