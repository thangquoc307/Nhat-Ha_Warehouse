package org.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.warehouse.dto.*;
import org.warehouse.model.Item;
import org.warehouse.model.Saler;
import org.warehouse.model.StockNote;
import org.warehouse.model.Warehouse;
import org.warehouse.repository.*;
import org.warehouse.service.IStockNoteService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private IItemRepository itemRepository;
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
            itemDetailDto.setHasSerial(itemDetailDto.getCount() == null);
            if (itemDetailDto.getCount() == null) {
                itemDetailDto.setCount(serialDtoList.size());
            }
        });
        return itemDetailDtos;
    }

    @Override
    public StockCreateDto getStockCreate(Integer id) {
        return stockNoteRepository.getStockNoteForEdit(id);
    }

    @Override
    public void modifyStock(StockCreateDto stockCreateDto) {
        StockNote stockNote = new StockNote(
                stockCreateDto.getId(),
                stockCreateDto.getReleaseDate(),
                stockCreateDto.getSo(),
                stockCreateDto.getPartner(),
                stockCreateDto.getNote(),
                stockCreateDto.getSalerId() == 0 ? null :
                        new Saler(stockCreateDto.getSalerId()),
                new Warehouse(stockCreateDto.getWarehouseId()));
        stockNoteRepository.save(stockNote);
    }

    @Override
    public ItemCreateDto getItemCreate(Integer id) {
        return itemRepository.getItemForEdit(id);
    }

    @Override
    public void modifyItem(ItemCreateDto itemCreateDto) {
        Item item = new Item(
                itemCreateDto.getId(),
                itemCreateDto.getPartNumber(),
                itemCreateDto.getDescription(),
                itemCreateDto.isHasSerial() ? null : 0,
                new StockNote(itemCreateDto.getStockNoteId())
        );
        itemRepository.save(item);
    }

    @Override
    public void createPdf(byte[] data, Integer id) {
        StockNote stockNote = stockNoteRepository.findById(id).get();
        if (stockNote != null) {
            stockNote.setImage(data);
            stockNoteRepository.save(stockNote);
        }
    }

    @Override
    public byte[] getImage(Integer id) {
        StockNote stockNote = stockNoteRepository.findById(id).get();
        return stockNote.getImage();
    }
}
