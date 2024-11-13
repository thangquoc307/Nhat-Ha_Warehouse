let debouncingArr = [];
let coverDate = (number) => {
    let date = new Date(number);
    let dateTime = date.getDate();
    let monthTime = date.getMonth() + 1;
    let yearTime = date.getFullYear();

    return `${dateTime < 10 ? "0" + dateTime : dateTime}/${monthTime < 10 ? "0" + monthTime : monthTime}/${yearTime}`
}
let getMonth = (number) => {
    let month = new Date(number).getMonth() + 1;
    return `T${month < 10 ? "0" : ""}${month}`
}
let debouncing = (func) => {
    debouncingArr.forEach(value => clearTimeout(value));
    debouncingArr.length = 0;
    debouncingArr.push(setTimeout(func, 200));
}

let showToast = (mess) => {
    $(function () {
        $("body").append(`<div id="lqt-toast">${mess}</div>`);
        setTimeout(function () {
            $("#lqt-toast").fadeOut(1000, function () {
                $("#lqt-toast").remove();
            });
        }, 3000);
    });
}
let countInArray = (array, item) => {
    let count = 0;
    array.forEach(value => {if (item === value) count++;})
    return count;
}
let reduceText = (text, size) => {
    if (text.length <= size) {
        return text;
    } else {
        return text.substring(0, size) + "...";
    }
}