let excelExport = (isInbound) => {
    $(function () {
        let warehouseId = $("#warehouse-select").val();
        let startDate = $("#start-date-input").val();
        let endDate = $("#end-date-input").val();

        let column = isInbound ? 8 : 9;
        let row = 3;

        let nameData;
        if (startDate !== "" && endDate !== "") {
            nameData = `From ${startDate} to ${endDate}`
        } else if (startDate !== "" && endDate === "") {
            nameData = `After ${startDate}`;
        } else if (startDate === "" && endDate !== "") {
            nameData = `Before ${endDate}`;
        } else {
            nameData = "All";
        }

        $.ajax({
            type: "GET",
            url: `${urlApi}warehouse/export-data?isInbound=${isInbound}&startDate=${startDate}&endDate=${endDate}&warehouseId=${warehouseId}`,
            success: (result, status, xhr) => {
                const workbook = new ExcelJS.Workbook();
                const sheet = workbook.addWorksheet();
                const borderStyle = {
                    top: { style: 'thin' },
                    left: { style: 'thin' },
                    bottom: { style: 'thin' },
                    right: { style: 'thin' }
                };

                sheet.getColumn('A').width = 15;
                sheet.getColumn('B').width = 25;
                sheet.getColumn('C').width = 50;
                sheet.getColumn('D').width = 15;
                sheet.getColumn('E').width = 20;
                sheet.getColumn('F').width = 20;
                sheet.getColumn('G').width = 30;
                if (isInbound) {
                    sheet.getColumn('H').width = 40;
                    sheet.addRow([
                        "DANH MỤC HÀNG HÓA NHẬP KHO", "", "", "", "",
                        `Từ ${startDate === "" ? "--/--/----" : coverDate(startDate)} đến ${endDate === "" ? "--/--/----" : coverDate(endDate)}`]);
                    sheet.mergeCells('A1:E1');
                    sheet.mergeCells('F1:H1');
                    sheet.addRow([]);
                    sheet.mergeCells('A2:H2');
                    sheet.addRow([
                        "NGÀY XUẤT",
                        "MÃ HÀNG",
                        "MÔ TẢ CHI TIẾT HÀNG HÓA",
                        "SỐ LƯỢNG",
                        "MÃ NHẬP KHO",
                        "NHẬP TỪ",
                        "SERIALS",
                        "GHI CHÚ"]);
                    result.forEach(value => {
                        let newRow;
                        value.itemDtos.forEach((item, ind) => {
                            newRow = sheet.addRow([
                                coverDate(value.releaseDate),
                                item.partNumber,
                                item.description,
                                item.count,
                                ind === 0 ? value.code : "",
                                ind === 0 ? value.fromTo : "",
                                item.serials ? item.serials.join("\n") : "",
                                ind === 0 ? value.note : ""
                            ])
                            row++;
                        })
                        if (value.itemDtos.length > 1) {
                            let end = newRow._number;
                            let begin = end - value.itemDtos.length + 1;
                            sheet.mergeCells(`E${begin}:E${end}`);
                            sheet.mergeCells(`F${begin}:F${end}`);
                            sheet.mergeCells(`H${begin}:H${end}`);
                        }
                    });
                } else {
                    sheet.getColumn('H').width = 20;
                    sheet.getColumn('I').width = 40;
                    sheet.addRow([
                        "DANH MỤC HÀNG HÓA XUẤT KHO", "", "", "", "",
                        `Từ ${startDate === "" ? "--/--/----" : coverDate(startDate)} đến ${endDate === "" ? "--/--/----" : coverDate(endDate)}`]);
                    sheet.mergeCells('A1:E1');
                    sheet.mergeCells('F1:I1');
                    sheet.addRow([]);
                    sheet.mergeCells('A2:I2');
                    sheet.addRow([
                        "NGÀY XUẤT",
                        "MÃ HÀNG",
                        "MÔ TẢ CHI TIẾT HÀNG HÓA",
                        "SỐ LƯỢNG",
                        "SỐ S.O",
                        "XUẤT CHO",
                        "SERIALS",
                        "SALER",
                        "GHI CHÚ"]);
                    result.forEach(value => {
                        let newRow;
                        value.itemDtos.forEach((item, ind) => {
                            newRow = sheet.addRow([
                                coverDate(value.releaseDate),
                                item.partNumber,
                                item.description,
                                item.count,
                                ind === 0 ? value.code : "",
                                ind === 0 ? value.fromTo : "",
                                item.serials ? item.serials.join("\n") : "",
                                ind === 0 ? value.saler : "",
                                ind === 0 ? value.note : ""
                            ])
                            row++;
                        })
                        if (value.itemDtos.length > 1) {
                            let end = newRow._number;
                            let begin = end - value.itemDtos.length + 1;
                            console.log(`${begin} - ${end}`)
                            sheet.mergeCells(`E${begin}:E${end}`);
                            sheet.mergeCells(`F${begin}:F${end}`);
                            sheet.mergeCells(`H${begin}:H${end}`);
                            sheet.mergeCells(`I${begin}:I${end}`);
                        }
                    });
                }

                let cur = "";
                let begin = 4;
                for (let i = begin; i <= row; i++) {
                    let cellValue = sheet.getRow(i).getCell('A').value;
                    if (cellValue !== cur) {
                        if (i - begin > 1) {
                            sheet.mergeCells(`A${begin}:A${i - 1}`);
                        }
                        cur = cellValue;
                        begin = i;
                    } else {
                        sheet.getRow(i).getCell('A').value = null;
                    }
                }

                if (row - begin > 0) {
                    sheet.mergeCells(`A${begin}:A${row}`);
                }

                sheet.getCell("A1").font = {
                    bold: true,
                    size: 16
                }
                sheet.getCell("F1").font = {
                    bold: true,
                }
                for (let rowNum = 1; rowNum <= row; rowNum++) {
                    for (let col = 1; col <= column; col++) {
                        const cellAddress = `${String.fromCharCode(64 + col)}${rowNum}`;
                        const cell = sheet.getCell(cellAddress);
                        cell.border = borderStyle;

                        if (rowNum === 3) {
                            cell.fill = {
                                type: 'pattern',
                                pattern: 'solid',
                                fgColor: { argb: 'FF6FA8DC' }
                            };
                            cell.font = {
                                color: { argb: 'FFFFFFFF' },
                                bold: true
                            };
                        }
                        if (col === 1 || col === 4 || col === 5) {
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
                    saveAs(blob, `[${nameData}]-Thống kê ${isInbound ? "nhập" : "xuất"} kho.xlsx`);
                });
            },
        })
    })
}