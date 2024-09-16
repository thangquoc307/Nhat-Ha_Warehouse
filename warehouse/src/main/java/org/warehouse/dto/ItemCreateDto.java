package org.warehouse.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateDto implements Validator {
    private Integer id;
    private String partNumber;
    @Column(length = 1000)
    private String description;
    private boolean hasSerial;
    private Integer stockId;
    private Integer manufacturerId;
    private int isInbound;

    public ItemCreateDto(Integer stockId, int isInbound) {
        this.stockId = stockId;
        this.isInbound = isInbound;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ItemCreateDto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ItemCreateDto target = (ItemCreateDto) o;
        if (target.getPartNumber().isBlank()) {
            errors.rejectValue("partNumber", null, "Part Number is Required");
        }
        if (target.getManufacturerId() == null || target.getManufacturerId() == 0) {
            errors.rejectValue("manufacturerId", null, "Manufacturer is Required");
        }

    }
}
