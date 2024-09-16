package org.warehouse.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOutboundDto implements Validator {
    private Integer id;
    private String so;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String partner;
    @Column(length = 1000)
    private String note;
    private Integer salerId;
    private Integer warehouseId;

    public CreateOutboundDto(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateOutboundDto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreateOutboundDto target = (CreateOutboundDto) o;
        if (target.getSo().isBlank()) {
            errors.rejectValue("so", null, "S.O is Required");
        }
        if (target.getReleaseDate() == null) {
            errors.rejectValue("releaseDate", null, "Release Date is Required");
        }
        if (target.getWarehouseId() == null || target.getWarehouseId() == 0) {
            errors.rejectValue("warehouseId", null, "Warehouse is Required");
        }
    }
}
