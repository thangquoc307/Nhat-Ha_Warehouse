package org.warehouse.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.warehouse.dto.CountDto;
import org.warehouse.dto.DataEditDto;
import org.warehouse.service.IModifyDataService;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/data")
public class CreateEditRestController {
    @Autowired
    private IModifyDataService modifyDataService;
    @PostMapping("warehouse")
    public ResponseEntity<?> createWarehouse(@RequestBody DataEditDto dataEditDto) {
        modifyDataService.createWarehouse(dataEditDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("warehouse")
    public ResponseEntity<?> editWarehouse(@RequestBody DataEditDto dataEditDto) {
        modifyDataService.editWarehouse(dataEditDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("saler")
    public ResponseEntity<?> createSaler(@RequestBody DataEditDto dataEditDto) {
        modifyDataService.createSaler(dataEditDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("saler")
    public ResponseEntity<?> editSaler(@RequestBody DataEditDto dataEditDto) {
        modifyDataService.editSaler(dataEditDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("serial")
    public ResponseEntity<?> editSerial(@RequestBody DataEditDto dataEditDto) {
        modifyDataService.editSerial(dataEditDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("serial/{id}")
    public ResponseEntity<?> createSerial(
            @PathVariable(name = "id") Integer id,
            @RequestBody List<String> serialList) {
        modifyDataService.createSerial(id, serialList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("count")
    public ResponseEntity<?> createCount(@RequestBody CountDto countDto) {
        modifyDataService.editCount(countDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
