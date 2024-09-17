package org.warehouse.dto.export;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExportItemDto {
    private Integer id;
    private String partNumber;
    private String description;
    private Integer count;
    private List<String> serials;

    public ExportItemDto(Integer id, String partNumber, String description, Integer count) {
        this.id = id;
        this.partNumber = partNumber;
        this.description = description;
        this.count = count;
    }
}
