package org.warehouse.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.warehouse.dto.CompareDto;
import org.warehouse.service.IDataInputService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/1.0/compare/")
public class CompareRestController {
    @Autowired
    private IDataInputService dataInputService;
    @GetMapping("data")
    public ResponseEntity<?> getData(
            @RequestParam(
                    name = "startDate",
                    required = false) LocalDate startDate,
            @RequestParam(
                    name = "endDate",
                    required = false) LocalDate endDate,
            @RequestParam(
                    name = "warehouseId",
                    required = false,
                    defaultValue = "1") Integer warehouseId) {
        List<CompareDto> compareDtos = dataInputService
                .getDataForCompare(startDate, endDate, warehouseId);
        if (compareDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(compareDtos, HttpStatus.OK);
        }
    }
}
