let idDelModal = "lqt-delete-modal";

let showDeleteModal = (id, name) => {
    $(function () {
        closeModal();
        $("body").append(`<div id="${idDelModal}" 
            class="boxshadow-outset borderradius color0 modal-confirm">
                <p>Are you sure about <span>Delete ${name}</span> ?</p>
                <button onclick="closeModal()">
                    <span class="material-symbols-outlined">close</span> Cancel
                </button>
                <button onclick="confirmDel('${id}', '${name}')">
                    <span class="material-symbols-outlined">delete</span> Delete
                </button>
            </div>`);
    });
}
let showLogoutModal = () => {
    $(function () {
        closeModal();
        $("body").append(`<div id="${idDelModal}" 
            class="boxshadow-outset borderradius color0 modal-confirm">
                <p>Are you sure about <span>LOGOUT</span> ?</p>
                <button onclick="closeModal()">
                    <span class="material-symbols-outlined">close</span> Cancel
                </button>
                <button onclick="gotoLink('/logout')">
                    <span class="material-symbols-outlined">logout</span> Logout
                </button>
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
let confirmDel = (id, name) => {
    deleteContent(id, name);
    closeModal();
}
let deleteContent = (id, name) => {
    $.ajax({
        type: "DELETE",
        url: `${urlApi + arrayLink[currentMode]}/delete/${id}`,
        success: (result, status, xhr) => {
            gotoLink(`http://localhost:8080/manage/device?mess=Delete ${name} success`);
        }
    })
}