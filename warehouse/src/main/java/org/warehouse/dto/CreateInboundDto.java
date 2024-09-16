package org.warehouse.dto;

import jakarta.persistence.*;
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
public class CreateInboundDto implements Validator {
    private Integer id;
    private String inboundCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String locationFrom;
    @Column(length = 1000)
    private String note;
    private Integer warehouseId;

    public CreateInboundDto(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateInboundDto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreateInboundDto target = (CreateInboundDto) o;
        if (target.getInboundCode().isBlank()) {
            errors.rejectValue("inboundCode", null, "Inbound Code is Required");
        }
        if (target.getReleaseDate() == null) {
            errors.rejectValue("releaseDate", null, "Release Date is Required");
        }
        if (target.getWarehouseId() == null || target.getWarehouseId() == 0) {
            errors.rejectValue("warehouseId", null, "Warehouse is Required");
        }
    }
}
