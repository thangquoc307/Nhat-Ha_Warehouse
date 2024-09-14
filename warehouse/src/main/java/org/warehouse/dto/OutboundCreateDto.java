//package org.warehouse.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class OutboundCreateDto implements Validator {
//    private Integer id;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate releaseDate;
//    private String so;
//    private String partner;
//    private String note;
//    private Integer salerId;
//    private Integer warehouseId;
//
//    public OutboundCreateDto(Integer warehouseId) {
//        this.warehouseId = warehouseId;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return OutboundCreateDto.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        OutboundCreateDto target = (OutboundCreateDto) o;
//        if (target.getReleaseDate() == null) {
//            errors.rejectValue("releaseDate",
//                    null, "Please choose release date");
//        }
//    }
//}