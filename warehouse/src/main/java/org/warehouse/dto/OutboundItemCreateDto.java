//package org.warehouse.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class OutboundItemCreateDto implements Validator {
//    private Integer id;
//    private String partNumber;
//    private String description;
//    private boolean hasSerial;
//    private Integer stockNoteId;
//
//    public OutboundItemCreateDto(Integer id, String partNumber, String description, Integer count, Integer stockNoteId) {
//        this.id = id;
//        this.partNumber = partNumber;
//        this.description = description;
//        this.hasSerial = count == null;
//        this.stockNoteId = stockNoteId;
//    }
//
//    public OutboundItemCreateDto(Integer stockNoteId) {
//        this.stockNoteId = stockNoteId;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return OutboundItemCreateDto.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        OutboundItemCreateDto target = (OutboundItemCreateDto) o;
//        if (target.getPartNumber().isBlank()) {
//            errors.rejectValue("partNumber",
//                    null, "Part Number is required");
//        }
//    }
//}
