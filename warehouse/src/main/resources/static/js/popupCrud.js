let closePopup = () => {
    $(`#popup`).fadeOut(500, function() {
        $(this).remove();
    });
}
let createWarehouse = () => {
    $(function () {
        let name = $("#popup-input-create").val();
    })

}
let showWareHouseManager = () => {
    $(function () {
        $.ajax({
            type: "GET",
            url: `${urlApi}warehouse/warehouse`,
            success: (result, status, xhr) => {
                let data = result.reduce((prev, cur) => {
                    return prev + `<div>
                        <div>${cur.name}</div>
                        <span class="material-symbols-outlined">edit</span>
                        <span class="material-symbols-outlined"
                            onclick="showDeleteModal('${cur.id}', '${cur.name}', '${mode.WAREHOUSE}')">delete</span>
                    </div>`;
                }, "");
                $("body").append(`<div id="popup">
                    <div class="content">
                        <h4 class="textAlert">WAREHOUSE MANAGE</h4>
                        <div class="input-group mb-3">
                          <input type="text" class="form-control" 
                            placeholder="new warehouse" id="popup-input-create">
                          <button class="btn btn-outline-success">
                            <span class="material-symbols-outlined">send</span>
                          </button>
                        </div>
                        <div class="serial-list-detail">${data}</div>
                        <div class="d-flex flex-row-reverse">
                            <div onclick="closePopup()"
                             class="button-close">Close</div>
                        </div>
                    </div>
                    <div class="filter"></div>
                </div>`)
            }
        })
    })
}

