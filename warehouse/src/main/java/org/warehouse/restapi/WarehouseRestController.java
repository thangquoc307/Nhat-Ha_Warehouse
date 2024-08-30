package org.warehouse.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/warehouse/")
public class WarehouseRestController {
    @GetMapping("show")
    public ResponseEntity<?> getItemForShow() {

    }
}
