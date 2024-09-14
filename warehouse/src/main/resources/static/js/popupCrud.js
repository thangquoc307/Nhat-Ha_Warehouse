let editList = [];
let idEdited = 0;
let isEdited = false;
class DataEdit {
    id;
    name;

    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
}
let closePopup = (isReload) => {
    if (isEdited && isReload) {
        gotoLink("/publish/home");
    } else {
        $(`#popup`).fadeOut(500, function() {
            $(this).remove();
        });
    }
}
let createData = (modeStt) => {
    $(function () {
        let value = $(`#popup-input-create`).val();
        if (value) {
            let data = new DataEdit(0, value);
            $.ajax({
                type: "POST",
                url: `${urlApi}data/${modeStt}`,
                contentType: "application/json",
                data: JSON.stringify(data),
                success: () => {
                    $(`#popup-input-create`).val("");
                    showWareHouseManager(modeStt);
                    isEdited = true;
                }
            })
        }
    })
}
let showWareHouseManager = (modeStt) => {
    $(function () {
        $.ajax({
            type: "GET",
            url: `${urlApi}warehouse/${modeStt}`,
            success: (result, status, xhr) => {
                editList = [];
                idEdited = 0;
                closePopup(false);
                let data = result.reduce((prev, cur) => {
                    editList.push(new DataEdit(cur.id, cur.name));
                    return prev + `<div id="edit-${cur.id}">
                        <div>${cur.name}</div>
                        <span class="material-symbols-outlined"
                            onclick="showEditData(${cur.id}, '${modeStt}')">edit</span>
                        <span class="material-symbols-outlined"
                            onclick="showDeleteModal('${cur.id}', '${cur.name}', '${modeStt}')">delete</span>
                    </div>`;
                }, "");
                $("body").append(`<div id="popup">
                    <div class="content">
                        <h4 class="textAlert">${modeStt.toUpperCase()} MANAGE</h4>
                        <div class="input-group mb-3">
                          <input type="text" class="form-control" 
                            placeholder="new warehouse" id="popup-input-create">
                          <button class="btn btn-outline-success">
                            <span class="material-symbols-outlined"
                                onclick="createData('${modeStt}')">send</span>
                          </button>
                        </div>
                        <div class="serial-list-detail mb-2">${data}</div>
                        <div class="d-flex flex-row-reverse">
                            <div onclick="closePopup(true)"
                             class="button-close">Close</div>
                        </div>
                    </div>
                    <div class="filter"></div>
                </div>`)
            }
        })
    })
}
let closeEditData = (id) => {
    $(function () {
        let data = editList.find(value => value.id == id);
        $(`#edit-${id}`).html(` <div>${data.name}</div>
            <span class="material-symbols-outlined"
                onclick="showEditData(${data.id})">edit</span>
            <span class="material-symbols-outlined"
                onclick="showDeleteModal('${data.id}', '${data.name}', '${mode.WAREHOUSE}')">delete</span>`)
    })
}
let sendEditData = (id, mode) => {
    $(function () {
        let value = $(`#edit-${id} input`).val();
        let data = new DataEdit(id, value);
        $.ajax({
            type: "PUT",
            url: `${urlApi}data/${mode}`,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: () => {
                showWareHouseManager(mode);
                isEdited = true;
            }
        })
    })
}
let showEditData = (id, mode) => {
    $(function () {
        closeEditData(idEdited);
        idEdited = id;
        let data = editList.find(value => value.id == id);
        $(`#edit-${id}`).html(`<input class="form-control" 
            type="text" value="${data.name}">
            <span class="material-symbols-outlined" 
                onclick="closeEditData(${data.id}, '${mode}')">close</span>
            <span class="material-symbols-outlined"
                onclick="sendEditData(${id}, '${mode}')">check</span>`)
    })
}

