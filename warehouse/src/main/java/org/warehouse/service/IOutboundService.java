package org.warehouse.service;

import org.springframework.data.domain.Page;
import org.warehouse.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface IOutboundService {
    Page<IOutboundItemShowDto> getOutboundForShow(String search, LocalDate startDate, LocalDate endDate, Integer warehouseId, Integer page, Integer size);
    Page<IInboundItemShowDto> getInboundForShow(String search, LocalDate startDate, LocalDate endDate, Integer warehouseId, Integer page, Integer size);
    List<IWarehouseDto> getAllWarehouse();
    List<ISalerDto> getAllSaler();
    List<IManufacturerDto> getAllManufacturer();
    List<ItemDetailDto> getOutbound(Integer outboundId);
    List<ItemDetailDto> getInbound(Integer inboundId);
    CreateInboundDto getInboundCreate(Integer id);
    void modifyInbound(CreateInboundDto createInboundDto);
    CreateOutboundDto getOutboundCreate(Integer id);
    void modifyOutbound(CreateOutboundDto createOutboundDto);

//    OutboundItemCreateDto getItemCreate(Integer id);
//    void modifyItem(OutboundItemCreateDto itemCreateDto);
    void createPdf(byte[] data, Integer id, Boolean isInbound);
    byte[] getImage(Integer id, Boolean isInbound);
}