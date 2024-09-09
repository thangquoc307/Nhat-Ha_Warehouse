package org.warehouse.dto;

import java.time.LocalDate;

public interface IDetailItemDto {
    Integer getStockNoteId();
    byte[] getImage();
    Integer getCountData();
    String getSo();
    LocalDate getReleaseDate();
}
