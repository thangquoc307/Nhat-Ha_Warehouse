package org.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailDto {
    private Integer id;
    private String partNumber;
    private String description;
    private Integer count;
    private List<IItemSerialDto> itemSerials;
    private Boolean hasSerial;

    public ItemDetailDto(Integer id, String partNumber, String description, Integer count) {
        this.id = id;
        this.partNumber = partNumber;
        this.description = description;
        this.count = count;
    }
}
