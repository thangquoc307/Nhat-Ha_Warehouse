package org.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.warehouse.model.*;
import org.warehouse.repository.*;
import org.warehouse.service.IDeleteService;

@Service
public class DeleteService implements IDeleteService {
    @Autowired
    private IWarehouseRepository warehouseRepository;
    @Autowired
    private IStockNoteRepository stockNoteRepository;
    @Autowired
    private IItemSerialRepository itemSerialRepository;
    @Autowired
    private ISalerRepository salerRepository;
    @Autowired
    private IItemRepository itemRepository;
    @Override
    public void deleteWarehouse(Integer warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).get();
        if (warehouse != null) {
            warehouse.setIsDelete(true);
            warehouseRepository.save(warehouse);
        }
    }

    @Override
    public void deleteStock(Integer stockId) {
        StockNote stockNote = stockNoteRepository.findById(stockId).get();
        if (stockNote != null) {
            stockNote.setIsDelete(true);
            stockNoteRepository.save(stockNote);
        }
    }

    @Override
    public void deleteItem(Integer itemId) {
        Item item = itemRepository.findById(itemId).get();
        if (item != null) {
            item.setIsDelete(true);
            itemRepository.save(item);
        }
    }

    @Override
    public void deleteSerial(Integer serialId) {
        ItemSerial itemSerial = itemSerialRepository.findById(serialId).get();
        if (itemSerial != null) {
            itemSerial.setIsDelete(true);
            itemSerialRepository.save(itemSerial);
        }
    }

    @Override
    public void deleteSaler(Integer salerId) {
        Saler saler = salerRepository.findById(salerId).get();
        if (saler != null) {
            saler.setIsDelete(true);
            salerRepository.save(saler);
        }
    }
}
