package org.warehouse.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
public class TransferCompareDto {
    private Integer idGet;
    private Boolean isInbound;
    private String code;
    private String partNumber;
    private String description;
    private Integer count;
    private String manufacturer;
    private LocalDate releaseDate;
    private Boolean hasImage;

    public TransferCompareDto(Integer idGet, String code, String partNumber, String description, Integer count, String manufacturer, LocalDate releaseDate, Boolean hasImage) {
        this.idGet = idGet;
        this.code = code;
        this.partNumber = partNumber;
        this.description = description;
        this.count = count;
        this.manufacturer = manufacturer;
        this.releaseDate = releaseDate;
        this.hasImage = hasImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferCompareDto that = (TransferCompareDto) o;
        return Objects.equals(partNumber, that.partNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber);
    }
}
