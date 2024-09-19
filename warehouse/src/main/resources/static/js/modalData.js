let idDelModal = "lqt-delete-modal";
let mode = {
    WAREHOUSE: "warehouse",
    OUTBOUND: "outbound",
    INBOUND: "inbound",
    OUTBOUND_ITEM: "outbound-item",
    INBOUND_ITEM: "inbound-item",
    OUTBOUND_SERIAL: "outbound-serial",
    INBOUND_SERIAL: "inbound-serial",
    SALER: "saler",
    MANUFACTURER: "manufacturer"
}

let showDeleteModal = (id, name, modeStt) => {
    if (modeStt == "stock") {
        modeStt = isInbound ? mode.INBOUND : mode.OUTBOUND;
    }
    if (modeStt == mode.OUTBOUND) {
        name = outboundChoosedName;
        id = outboundChoosedId;
    } else if (modeStt == mode.INBOUND) {
        name = inboundChoosedName;
        id = inboundChoosedId;
    }
    if (id == 0) {
        return;
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
    $.ajax({
        type: "DELETE",
        url: `${urlApi}delete/${sttMode}/${id}`,
        success: (result, status, xhr) => {
            switch (sttMode) {
                case mode.WAREHOUSE:
                    gotoLink("/publish/home");
                    break;
                case mode.OUTBOUND:
                case mode.INBOUND:
                    resetDetail();
                    pageableLoad(pagePageable);
                    break;
                case mode.INBOUND_ITEM:
                case mode.OUTBOUND_ITEM:
                case mode.INBOUND_SERIAL:
                case mode.OUTBOUND_SERIAL:
                    pageableLoad(pagePageable);
                    showDetail(null, "");
                    break;
                case mode.SALER:
                case mode.MANUFACTURER:
                    break;
            }
        }
    })
}