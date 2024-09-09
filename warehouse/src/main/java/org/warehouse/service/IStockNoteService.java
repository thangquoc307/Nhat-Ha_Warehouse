package org.warehouse.service;

import org.springframework.data.domain.Page;
import org.warehouse.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface IStockNoteService {
    Page<IItemShowDto> getStockNoteForShow(String search, LocalDate startDate, LocalDate endDate, Integer warehouseId, Integer page, Integer size);
    List<IWarehouseDto> getAllWarehouse();
    List<ISalerDto> getAllSaler();
    List<ItemDetailDto> getItemByStock(Integer stockId);
    StockCreateDto getStockCreate(Integer id);
    void modifyStock(StockCreateDto stockCreateDto);
    ItemCreateDto getItemCreate(Integer id);
    void modifyItem(ItemCreateDto itemCreateDto);
    void createPdf(byte[] data, Integer id);
    byte[] getImage(Integer id);
}
