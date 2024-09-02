package org.warehouse.dto;

import java.time.LocalDate;

public interface IItemShowDto {
    Integer getId();
    String getNote();
    String getPartner();
    LocalDate getReleaseDate();
    String getSo();
    String getDescription();
    String getPartNumber();
    String getName();
    Integer getCount();
}
