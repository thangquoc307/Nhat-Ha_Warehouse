let debouncingArr = [];
let coverDate = (number) => {
    let date = new Date(number);
    let dateTime = date.getDate();
    let monthTime = date.getMonth() + 1;
    let yearTime = date.getFullYear();

    return `${dateTime < 10 ? "0" + dateTime : dateTime}/${monthTime < 10 ? "0" + monthTime : monthTime}/${yearTime}`
}

let coverPrize = (number) => {
    let numberStr = "" + number;
    let result = "";
    let count = 0;
    for (let i = numberStr.length - 1; i >= 0; i--) {
        if (count % 3 == 0 && count != 0) {
            result = "," + result;
        }
        count++;
        result = numberStr[i] + result;
    }
    return result;
}
let checkLazyload = (id) => {
    const safeDistance = 50;

    let container = document.getElementById(id);
    let scrollTop = container.scrollTop;
    let scrollHeight = container.scrollHeight;
    let clientHeight = container.clientHeight;

    return scrollTop + clientHeight >= scrollHeight - safeDistance;
}
let debouncing = (func) => {
    debouncingArr.forEach(value => clearTimeout(value));
    debouncingArr.length = 0;
    debouncingArr.push(setTimeout(func, 500));
}
let hourSymbol = ["🕐","🕑","🕒","🕓","🕔","🕕","🕖","🕗","🕘","🕙","🕚","🕛"];
let addHourSymbol = (time) => {
    let symbol = hourSymbol[(+time.split(":")[0] - 1) % 12];
    return symbol + " " + time;
}
let coverTimeDuration = (minute) => {
    minute /= 60;
    let minus = minute % 60;
    let hour = Math.floor(minute / 60);
    return `⏳ ${hour} hours : ${minus} minutes`;
}
let roundToThreeDecimalPlaces = (num) => {
    return num.toFixed(2);
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