package org.warehouse.service;

public interface IDeleteService {
    void deleteWarehouse(Integer warehouseId);
    void deleteOutbound(Integer outboundId);
    void deleteInbound(Integer inboundId);
    void deleteOutboundItem(Integer outboundItemId);
    void deleteInboundItem(Integer inboundItemId);
    void deleteOutboundSerial(Integer serialId);
    void deleteInboundSerial(Integer serialId);
    void deleteSaler(Integer salerId);
    void deleteManufacturer(Integer manufacturerId);
}
