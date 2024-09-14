package org.warehouse.dto;

import java.time.LocalDate;

public interface IInboundItemShowDto {
    Integer getId();
    byte[] getImage();
    String getInboundCode();
    String getLocationFrom();
    String getNote();
    LocalDate getReleaseDate();
    Integer getCount();
    String getDescription();
    String getPartNumber();
    String getName();
}
