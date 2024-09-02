package org.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.warehouse.dto.*;
import org.warehouse.model.Item;
import org.warehouse.repository.IItemSerialRepository;
import org.warehouse.repository.ISalerRepository;
import org.warehouse.repository.IStockNoteRepository;
import org.warehouse.repository.IWarehouseRepository;
import org.warehouse.service.IStockNoteService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockNoteService implements IStockNoteService {
    @Autowired
    private IStockNoteRepository stockNoteRepository;
    @Autowired
    private IWarehouseRepository warehouseRepository;
    @Autowired
    private IItemSerialRepository iItemSerialRepository;
    @Autowired
    private ISalerRepository salerRepository;
    @Override
    public Page<IItemShowDto> getStockNoteForShow(
            String search, LocalDate startDate, LocalDate endDate,
            Integer warehouseId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return stockNoteRepository.getItemDto(
                "%" + search + "%", startDate, endDate, warehouseId, pageable);
    }

    @Override
    public List<IWarehouseDto> getAllWarehouse() {
        return warehouseRepository.getAllWarehouse();
    }

    @Override
    public List<ISalerDto> getAllSaler() {
        return salerRepository.getAllSaler();
    }

    @Override
    public List<ItemDetailDto> getItemByStock(Integer stockId) {
        List<ItemDetailDto> itemDetailDtos =
                stockNoteRepository.getAllItemByStock(stockId);
        itemDetailDtos.stream().forEach(itemDetailDto -> {
            List<IItemSerialDto> serialDtoList =
                    iItemSerialRepository.getItemSerial(itemDetailDto.getId());
            itemDetailDto.setItemSerials(serialDtoList);
            if (itemDetailDto.getCount() == null) {
                itemDetailDto.setCount(serialDtoList.size());
            }
        });
        return itemDetailDtos;
    }
}
