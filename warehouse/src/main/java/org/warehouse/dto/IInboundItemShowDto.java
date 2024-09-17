package org.warehouse.dto;

import java.time.LocalDate;

public interface IInboundItemShowDto {
    Integer getId();
    String getInboundCode();
    String getLocationFrom();
    String getNote();
    LocalDate getReleaseDate();
    Integer getCount();
    String getDescription();
    String getPartNumber();
    byte[] getImage();
    String getName();
}
