let data = [];
class DataExcel {
    partNumber;
    description;
    stock;
    inboundCount;
    outboundCount;
    finalCount;

    constructor(partNumber, description, stock, inboundCount, outboundCount, finalCount) {
        this.partNumber = partNumber;
        this.description = description;
        this.stock = stock;
        this.inboundCount = inboundCount;
        this.outboundCount = outboundCount;
        this.finalCount = finalCount;
    }
}
let setupData = () => {
    $(function () {
        let warehouseId = $("#warehouse-select").val();
        let startDate = $("#start-date-input").val();
        let endDate = $("#end-date-input").val();
        $.ajax({
            url: `${urlApi}compare/data?warehouseId=${warehouseId}`
                + `&startDate=${startDate}&endDate=${endDate}`,
            type: "GET",
            success: (result, status, xhr) => {
                data = [];
                $("#data-compare").html(
                    result.reduce((prev, cur, index) => {
                        let finalCount = (cur.stock ?? 0) + (cur.inboundCount ?? 0) - (cur.outboundCount ?? 0);
                        let textColor = "";
                        data.push(new DataExcel(
                            cur.partNumber, cur.description, cur.stock, cur.inboundCount, cur.outboundCount, finalCount));
                        if (finalCount > 0) {
                            textColor = "text-success";
                        } else if (finalCount < 0) {
                            textColor = "text-danger";
                        }
                        return prev + `<tr>
                            <td class="text-center">${index + 1}</td>
                            <td title="${cur.manufacturer ?? ""}">
                                ${cur.manufacturer ?? ""}</td>
                            <td title="${cur.partNumber}">
                                ${cur.partNumber}</td>
                            <td title="${cur.description}">
                                ${cur.description}</td>
                            <td class="text-center table-compare-count">
                                ${cur.stock ?? "0"}</td>
                            <td class="text-center table-compare-count">
                                ${cur.inboundCount ?? "0"}</td>
                            <td class="text-center table-compare-count">
                                ${cur.outboundCount ?? "0"}</td>
                            <th class="text-center table-compare-count fs-5 ${textColor}">
                                ${finalCount}</th>
                            <td class="table-compare-note"><div class="d-flex flex-column gap-2">
                                ${cur.inboundList.reduce((prev, cur) => {
                                    return prev + `<div class="table-compare-box">
                                        <div class="table-compare-count-text">${cur.count}</div>
                                        <div>${coverDate(cur.releaseDate)}</div>
                                        <div>${cur.name ?? ""}</div>` +
                                        (cur.hasImage 
                                            ? `<div class="button-image-active 
                                                material-symbols-outlined"
                                                onclick="showPdf(${cur.idGet}, true)">image</div>`
                                            : `<div class="button-image-inactive 
                                                material-symbols-outlined">image</div>`)
                                + `</div>`
                                }, "")}
                            </td>
                            <td class="table-compare-note"><div class="d-flex flex-column gap-2">
                                      ${cur.outboundList.reduce((prev, cur) => {
                            return prev + `<div class="table-compare-box">
                                        <div class="table-compare-count-text">${cur.count}</div>
                                        <div>${coverDate(cur.releaseDate)}</div>
                                        <div>${cur.name ?? ""}</div>` +
                                        (cur.hasImage
                                            ? `<div class="button-image-active 
                                                material-symbols-outlined"
                                                onclick="showPdf(${cur.idGet}, false)">image</div>`
                                            : `<div class="button-image-inactive 
                                                material-symbols-outlined">image</div>`)
                                        + `</div>`
                                }, "")}
                            </td>
                        </tr>`;
                    }, "")
                );
            }
        })
    })
}
let exportExcel = () => {
    const workbook = new ExcelJS.Workbook();
    const sheet = workbook.addWorksheet('My Sheet');

    let startDate = $("#start-date-input").val();
    let endDate = $("#end-date-input").val();

    sheet.addRow([
        "DANH MỤC HÀNG HÓA VÀ NHẬP XUẤT TỒN KHO", "", "",
        "Từ Ngày", startDate == "" ? "" : coverDate(startDate),
        "Đến Ngày", endDate == "" ? "" : coverDate(endDate)])
    sheet.mergeCells('A1:C1');
    sheet.addRow([]);
    sheet.mergeCells('A2:G2');
    sheet.addRow(["STT", "MÃ HÀNG", "MÔ TẢ CHI TIẾT HÀNG HÓA", "TỒN ĐẦU", "SL NHẬP", "SL XUẤT", "TỒN CUỐI", "NOTE"]);
    data.forEach((value, index) => {
        sheet.addRow([
            index + 1,
            value.partNumber,
            value.description,
            value.stock,
            value.inboundCount,
            value.outboundCount,
            value.finalCount,
            ""
        ])
    })
    sheet.getColumn('A').width = 8;
    sheet.getColumn('B').width = 25;
    sheet.getColumn('C').width = 50;
    sheet.getColumn('D').width = 15;
    sheet.getColumn('E').width = 15;
    sheet.getColumn('F').width = 15;
    sheet.getColumn('G').width = 15;
    sheet.getColumn('H').width = 50;

    const borderStyle = {
        top: { style: 'thin' },
        left: { style: 'thin' },
        bottom: { style: 'thin' },
        right: { style: 'thin' }
    };

    sheet.getCell("A1").font = {
        bold: true,
        size: 16
    }
    sheet.getCell("D1").font = {
        bold: true,
    }
    sheet.getCell("F1").font = {
        bold: true,
    }

    for (let row = 1; row <= data.length + 3; row++) {
        for (let col = 1; col <= 8; col++) {
            const cellAddress = `${String.fromCharCode(64 + col)}${row}`;
            const cell = sheet.getCell(cellAddress);
            cell.border = borderStyle;

            if (row === 3) {
                cell.fill = {
                    type: 'pattern',
                    pattern: 'solid',
                    fgColor: { argb: 'FF6FA8DC' }
                };
                cell.font = {
                    color: { argb: 'FFFFFFFF' },
                    bold: true
                };
            } else if (row > 3) {
                if (col === 2) {
                    cell.fill = {
                        type: 'pattern',
                        pattern: 'solid',
                        fgColor: { argb: 'FFCFE2F3' }
                    };
                } else if (col === 4) {
                    cell.fill = {
                        type: 'pattern',
                        pattern: 'solid',
                        fgColor: { argb: 'FFFCE5CD' }
                    };
                }
            }

            if (!(row >= 3 && (col === 2 || col === 3 || col === 8))) {
                cell.alignment = {
                    horizontal: 'center',
                    vertical: 'middle',
                    wrapText: true
                };
            } else {
                cell.alignment = {
                    vertical: 'middle',
                    wrapText: true
                };
            }
        }
    }

    workbook.xlsx.writeBuffer().then(function(data) {
        const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
        saveAs(blob, 'NH-Warehouse.xlsx');
    });
}
setupData();