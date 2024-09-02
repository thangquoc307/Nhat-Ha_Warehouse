package org.warehouse.service;

import org.springframework.data.domain.Page;
import org.warehouse.dto.IItemShowDto;
import org.warehouse.dto.ISalerDto;
import org.warehouse.dto.IWarehouseDto;
import org.warehouse.dto.ItemDetailDto;

import java.time.LocalDate;
import java.util.List;

public interface IStockNoteService {
    Page<IItemShowDto> getStockNoteForShow(String search, LocalDate startDate, LocalDate endDate, Integer warehouseId, Integer page, Integer size);
    List<IWarehouseDto> getAllWarehouse();
    List<ISalerDto> getAllSaler();
    List<ItemDetailDto> getItemByStock(Integer stockId);
}
