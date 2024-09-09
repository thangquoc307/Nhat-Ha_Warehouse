package org.warehouse.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.warehouse.dto.IItemShowDto;
import org.warehouse.dto.ISalerDto;
import org.warehouse.dto.IWarehouseDto;
import org.warehouse.dto.ItemDetailDto;
import org.warehouse.service.IStockNoteService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/1.0/warehouse/")
public class WarehouseRestController {
    @Autowired
    private IStockNoteService stockNoteService;
    @GetMapping("show")
    public ResponseEntity<?> getItemForShow(
            @RequestParam(
                    name = "search",
                    required = false,
                    defaultValue = "") String search,
            @RequestParam(
                    name = "startDate",
                    required = false) LocalDate startDate,
            @RequestParam(
                    name = "endDate",
                    required = false) LocalDate endDate,
            @RequestParam(
                    name = "warehouseId",
                    required = false,
                    defaultValue = "1") Integer warehouseId,
            @RequestParam(
                    name = "page",
                    required = false,
                    defaultValue = "0") Integer page,
            @RequestParam(
                    name = "size",
                    required = false,
                    defaultValue = "12") Integer size) {
        Page<IItemShowDto> iItemShowDtoPage = stockNoteService.getStockNoteForShow(search, startDate, endDate, warehouseId, page, size);
        if (iItemShowDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(iItemShowDtoPage, HttpStatus.OK);
        }
    }
    @GetMapping("item-detail")
    public ResponseEntity<?> getItemDetail(
            @RequestParam(name = "stockId") Integer stockId) {
        List<ItemDetailDto> iItemDetailDtos = stockNoteService.getItemByStock(stockId);
        if (iItemDetailDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(iItemDetailDtos, HttpStatus.OK);
        }
    }
    @GetMapping("warehouse")
    public ResponseEntity<?> getALlWarehouse() {
        List<IWarehouseDto> warehouseDtoList = stockNoteService.getAllWarehouse();
        if (warehouseDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(warehouseDtoList, HttpStatus.OK);
        }
    }
    @GetMapping("saler")
    public ResponseEntity<?> getALlSaler() {
        List<ISalerDto> salerDtos = stockNoteService.getAllSaler();
        if (salerDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(salerDtos, HttpStatus.OK);
        }
    }
    @PostMapping("/upload-pdf/{id}")
    public ResponseEntity<?> uploadPdfFile(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                byte[] fileBytes = file.getBytes();
                stockNoteService.createPdf(fileBytes, id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("pdf/{id}")
    public ResponseEntity<?> getPdf(@PathVariable Integer id) {
        byte[] image = stockNoteService.getImage(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline()
                .filename("file.pdf").build());
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
