package org.warehouse.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataCompareDto {
    private String partNumber;
    private Integer countInput;
    private List<IDetailItemDto> detailItem;

    public DataCompareDto(String partNumber) {
        this.partNumber = partNumber;
    }
}
