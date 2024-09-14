//package org.warehouse.restapi;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.warehouse.dto.DataCompareDto;
//import org.warehouse.model.DataInput;
//import org.warehouse.service.IDataInputService;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/1.0/compare/")
//public class CompareRestController {
//    @Autowired
//    private IDataInputService dataInputService;
//    @PostMapping("send")
//    public ResponseEntity<?> insertData(
//            @RequestBody List<DataInput> dataExcelList) {
//        dataInputService.insertDataInput(dataExcelList);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @GetMapping("data")
//    public ResponseEntity<?> getData(
//            @RequestParam(
//                    name = "startDate",
//                    required = false) LocalDate startDate,
//            @RequestParam(
//                    name = "endDate",
//                    required = false) LocalDate endDate,
//            @RequestParam(
//                    name = "warehouseId",
//                    required = false,
//                    defaultValue = "1") Integer warehouseId) {
//        List<DataCompareDto> dataCompareDtos =
//                dataInputService.getDataForCompare(startDate, endDate, warehouseId);
//        if (dataCompareDtos.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(dataCompareDtos, HttpStatus.OK);
//        }
//    }
//}
