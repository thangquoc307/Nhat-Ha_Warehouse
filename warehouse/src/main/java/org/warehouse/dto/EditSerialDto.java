package org.warehouse.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditSerialDto {
    private Integer id;
    private String name;
    private Boolean isInbound;
}
