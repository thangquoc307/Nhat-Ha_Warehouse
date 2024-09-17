package org.warehouse.dto.export;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExportStockDto {
    private Integer id;
    private LocalDate releaseDate;
    private String code;
    private String fromTo;
    private String saler;
    private String note;
    private List<ExportItemDto> itemDtos;

    public ExportStockDto(Integer id, LocalDate releaseDate, String code, String fromTo, String note) {
        this.id = id;
        this.releaseDate = releaseDate;
        this.code = code;
        this.fromTo = fromTo;
        this.note = note;
    }

    public ExportStockDto(Integer id, LocalDate releaseDate, String code, String fromTo, String saler, String note) {
        this.id = id;
        this.releaseDate = releaseDate;
        this.code = code;
        this.fromTo = fromTo;
        this.saler = saler;
        this.note = note;
    }
}
