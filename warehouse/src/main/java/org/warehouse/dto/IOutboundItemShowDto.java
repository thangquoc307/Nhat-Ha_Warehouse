package org.warehouse.dto;

import java.time.LocalDate;

public interface IOutboundItemShowDto {
    Integer getId();
    String getSo();
    String getPartner();
    String getNote();
    LocalDate getReleaseDate();
    Integer getCount();
    String getDescription();
    String getPartNumber();
    String getName();
    byte[] getImage();
    String getManufacturer();
}
