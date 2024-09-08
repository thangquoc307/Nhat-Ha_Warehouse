let idDelModal = "lqt-delete-modal";
let mode = {
    WAREHOUSE: "warehouse",
    STOCK: "stock",
    ITEM: "item",
    SERIAL: "serial",
    SALER: "saler"
}

let showDeleteModal = (id, name, modeStt) => {
    if (modeStt == mode.STOCK) {
        name = stockName;
        id = stockChoosedId;
    }
    $(function () {
        closePopup();
        closeModal();
        $("body").append(`<div id="${idDelModal}" >
                <div></div>
                <div class="boxshadow-outset borderradius color0 modal-confirm">
                    <p>Are you sure about <span>Delete ${name}</span> ?</p>
                    <button onclick="closeModal()">
                        <span class="material-symbols-outlined">close</span> Cancel
                    </button>
                    <button onclick="confirmDel('${id}', '${modeStt}')">
                        <span class="material-symbols-outlined">delete</span> Delete
                    </button>
                </div>
               
            </div>`);
    });
}
let closeModal = () => {
    $(`#${idDelModal}`).fadeOut(500, function() {
        $(this).remove();
    });
}
let gotoLink = (link) => {
    window.location.href = link;
}
let confirmDel = (id, mode) => {
    deleteContent(id, mode);
    closeModal();
}
let deleteContent = (id, sttMode) => {
    console.log(`${urlApi}delete/${sttMode}/${id}`)
    $.ajax({
        type: "DELETE",
        url: `${urlApi}delete/${sttMode}/${id}`,
        success: (result, status, xhr) => {
            switch (sttMode) {
                case mode.WAREHOUSE:
                    gotoLink("/publish/home");
                    break;
                case mode.STOCK:
                    resetDetail();
                    pageableLoad(pagePageable);
                    break;
                case mode.ITEM:
                case mode.SERIAL:
                    pageableLoad(pagePageable);
                    showDetail(null, "");
                    break;
                case mode.SALER:
                    break;
            }

        }
    })
}