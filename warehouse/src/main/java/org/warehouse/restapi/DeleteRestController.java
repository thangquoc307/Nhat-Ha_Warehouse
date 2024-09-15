package org.warehouse.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.warehouse.service.IDeleteService;

@RestController
@RequestMapping("/api/1.0/delete/")
public class DeleteRestController {
    @Autowired
    private IDeleteService deleteService;
    @DeleteMapping("{mode}/{id}")
    public ResponseEntity<?> deleteItem(
            @PathVariable String mode,
            @PathVariable Integer id) {
        switch (mode) {
            case "warehouse" -> deleteService.deleteWarehouse(id);
            case "outbound" -> deleteService.deleteOutbound(id);
            case "inbound" -> deleteService.deleteInbound(id);
            case "outbound-item" -> deleteService.deleteOutboundItem(id);
            case "inbound-item" -> deleteService.deleteInboundItem(id);
            case "outbound-serial" -> deleteService.deleteOutboundSerial(id);
            case "inbound-serial" -> deleteService.deleteInboundSerial(id);
            case "saler" -> deleteService.deleteSaler(id);
            case "manufacturer" -> deleteService.deleteManufacturer(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
