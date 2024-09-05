package org.warehouse.service;

public interface IDeleteService {
    void deleteWarehouse(Integer warehouseId);
    void deleteStock(Integer stockId);
    void deleteItem(Integer itemId);
    void deleteSerial(Integer serialId);
    void deleteSaler(Integer salerId);
}
