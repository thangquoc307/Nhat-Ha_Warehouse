package org.warehouse.dto;

import java.time.LocalDate;

public interface IOutboundItemShowDto {
    Integer getId();
    String getNote();
    String getPartner();
    LocalDate getReleaseDate();
    String getSo();
    String getDescription();
    String getPartNumber();
    String getName();
    Integer getCount();
    byte[] getImage();
    String getManufacturer();
}
