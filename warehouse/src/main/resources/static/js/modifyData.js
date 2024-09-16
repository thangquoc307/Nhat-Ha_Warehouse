let listSerialInsert = [];
let idCreateSerial = 'lqt-table-serial';
let itemIdChoosed = 0;
let showTableCreateSerial = (itemId, name) => {
    $(function () {
        itemIdChoosed = itemId;
        closeTableCreateSerial();
        $("body").append(`<div id="${idCreateSerial}">
            <div></div>
            <div class="color0 boxshadow-outset borderradius">
                <h4>Create serial for <span class="textAlert">${name}</span></h4>
                <div class="input-group">
                    <input id="input-serial" type="text" class="form-control" 
                        placeholder="Nhập Serial">
                    <button class="btn btn-outline-success" type="button"
                        onclick="addSerial()">
                        <span class="material-symbols-outlined">add_row_above</span>
                    </button>
                </div>
                <small class="textAlert">** Nhập từng SERIAL hoặc 1 list SERIAL ngăn cách bằng dấu cách (VD: CNQDLJ6G33 CNQDLJ6G34)</small>
                <div id="display-serial" 
                    class="mt-3 color3 boxshadow-inset 
                    scroll-custom d-flex flex-column gap-2 p-3"></div>
                <div class="gap-2 mt-3 table-button">
                    <div id="count-serial">Số lượng: ${listSerialInsert.length}</div>
                    <button onclick="closeTableCreateSerial()">
                        <span class="material-symbols-outlined">close</span> Cancel
                    </button>
                    <button onclick="createSerial()">
                        <span class="material-symbols-outlined">send</span> Send
                    </button>
                </div>
            </div>
        </div>`)
    })
}
let addSerial = () => {
    $(function () {
        let value = $("#input-serial").val().split(" ");
        let newSerial = value.filter(val => val.length != 0);
        newSerial.forEach(val => listSerialInsert.push(val));
        $("#input-serial").val("");
        showSerialCreate();
        updateCount();
    })
}
let updateCount = () => {
    $(function () {
        $("#count-serial").html(`Số lượng: ${listSerialInsert.length}`);
    })
}
let closeTableCreateSerial = () => {
    $(`#${idCreateSerial}`).fadeOut(500, function() {
        $(this).remove();
    });
}
let showSerialCreate = () => {
    $(function () {
        $("#display-serial").html(listSerialInsert.reduce((prev, cur, index) => {
            return prev + `<div class="serial-create-item color0">
                <div>${cur}</div>
                <span class="material-symbols-outlined"
                    onclick="deleteSerialCreate(${index})">delete</span>
            </div>`;
        }, ""));

    })

}
let deleteSerialCreate = async (index) => {
    listSerialInsert.splice(index, 1);
    await showSerialCreate();
    updateCount();
}
let createSerial = () => {
    $(function () {
        if (listSerialInsert.length > 0) {
            $.ajax({
                type: "POST",
                url: `${urlApi}data/serial/${itemIdChoosed}/${isInboundSerial}`,
                contentType: "application/json",
                data: JSON.stringify(listSerialInsert),
                success: () => {
                    closeTableCreateSerial();
                    showDetail(null, "");
                }
            })
        }
    })

}
