package org.warehouse.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.warehouse.dto.*;
import org.warehouse.dto.export.ExportStockDto;
import org.warehouse.service.IOutboundService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/1.0/warehouse/")
public class WarehouseRestController {
    @Autowired
    private IOutboundService outboundService;
    @GetMapping("show-outbound")
    public ResponseEntity<?> getOutboundItemForShow(
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
        Page<IOutboundItemShowDto> iItemShowDtoPage = outboundService.getOutboundForShow(search, startDate, endDate, warehouseId, page, size);
        if (iItemShowDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(iItemShowDtoPage, HttpStatus.OK);
        }
    }
    @GetMapping("show-inbound")
    public ResponseEntity<?> getInboundItemForShow(
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
        Page<IInboundItemShowDto> iItemShowDtoPage = outboundService.getInboundForShow(search, startDate, endDate, warehouseId, page, size);
        if (iItemShowDtoPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(iItemShowDtoPage, HttpStatus.OK);
        }
    }
    @GetMapping("outbound-item-detail")
    public ResponseEntity<?> getOutboundItemDetail(
            @RequestParam(name = "outboundId") Integer outboundId) {
        List<ItemDetailDto> iItemDetailDtos = outboundService.getOutbound(outboundId);
        if (iItemDetailDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(iItemDetailDtos, HttpStatus.OK);
        }
    }
    @GetMapping("inbound-item-detail")
    public ResponseEntity<?> getInboundItemDetail(
            @RequestParam(name = "inboundId") Integer inboundId) {
        List<ItemDetailDto> iItemDetailDtos = outboundService.getInbound(inboundId);
        if (iItemDetailDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(iItemDetailDtos, HttpStatus.OK);
        }
    }
    @GetMapping("warehouse")
    public ResponseEntity<?> getALlWarehouse() {
        List<IWarehouseDto> warehouseDtoList = outboundService.getAllWarehouse();
        if (warehouseDtoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(warehouseDtoList, HttpStatus.OK);
        }
    }
    @GetMapping("saler")
    public ResponseEntity<?> getALlSaler() {
        List<ISalerDto> salerDtos = outboundService.getAllSaler();
        if (salerDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(salerDtos, HttpStatus.OK);
        }
    }
    @GetMapping("manufacturer")
    public ResponseEntity<?> getALlManufacturer() {
        List<IManufacturerDto> manufacturerDtos = outboundService.getAllManufacturer();
        if (manufacturerDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(manufacturerDtos, HttpStatus.OK);
        }
    }
    @PostMapping("/upload-pdf/{id}")
    public ResponseEntity<?> uploadPdfFile(
            @PathVariable Integer id,
            @RequestParam("file") MultipartFile file,
            @RequestParam(name = "isInbound") Boolean isInbound) {
        try {
            if (!file.isEmpty()) {
                byte[] fileBytes = file.getBytes();
                outboundService.createPdf(fileBytes, id, isInbound);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("pdf/{id}")
    public ResponseEntity<?> getPdf(
            @PathVariable Integer id,
            @RequestParam(name = "isInbound") Boolean isInbound) {
        byte[] image = outboundService.getImage(id, isInbound);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline()
                .filename("file.pdf").build());
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
    @GetMapping("export-data")
    public ResponseEntity<?> getOutboundItemForExport(
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
            @RequestParam(name = "isInbound") boolean isInbound) {
        List<ExportStockDto> exportStockDtos = outboundService.getDataForExport(
                startDate, endDate, warehouseId, isInbound);
        if (exportStockDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(exportStockDtos, HttpStatus.OK);
        }
    }
}
