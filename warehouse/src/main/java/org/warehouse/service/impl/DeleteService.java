package org.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.warehouse.repository.*;
import org.warehouse.service.IDeleteService;

@Service
public class DeleteService implements IDeleteService {
    @Autowired
    private IWarehouseRepository warehouseRepository;
    @Autowired
    private IOutboundRepository outboundRepository;
    @Autowired
    private IInboundRepository inboundRepository;
    @Autowired
    private IOutboundItemRepository outboundItemRepository;
    @Autowired
    private IInboundItemRepository inboundItemRepository;
    @Autowired
    private IOutboundItemSerialRepository outboundItemSerialRepository;
    @Autowired
    private IInboundItemSerialRepository inboundItemSerialRepository;
    @Autowired
    private ISalerRepository salerRepository;
    @Autowired
    private IManufactuterRepository manufactuterRepository;
    @Override
    public void deleteWarehouse(Integer warehouseId) {
        Boolean exist = warehouseRepository.existsById(warehouseId);
        if (exist) {
            warehouseRepository.deleteWarehouse(warehouseId);
        }
    }
    @Override
    public void deleteOutbound(Integer outboundId) {
        Boolean exist = outboundRepository.existsById(outboundId);
        if (exist) {
            outboundRepository.deleteOutbound(outboundId);
        }
    }
    @Override
    public void deleteInbound(Integer inboundId) {
        Boolean exist = inboundRepository.existsById(inboundId);
        if (exist) {
            inboundRepository.deleteInbound(inboundId);
        }
    }
    @Override
    public void deleteOutboundItem(Integer outboundItemId) {
        Boolean exist = outboundItemRepository.existsById(outboundItemId);
        if (exist) {
            outboundItemRepository.deleteOutboundItem(outboundItemId);
        }
    }
    @Override
    public void deleteInboundItem(Integer inboundItemId) {
        Boolean exist = inboundItemRepository.existsById(inboundItemId);
        if (exist) {
            inboundItemRepository.deleteInboundItem(inboundItemId);
        }
    }
    @Override
    public void deleteOutboundSerial(Integer serialId) {
        Boolean exist = outboundItemSerialRepository.existsById(serialId);
        if (exist) {
            outboundItemSerialRepository.deleteOutboundSerial(serialId);
        }
    }
    @Override
    public void deleteInboundSerial(Integer serialId) {
        Boolean exist = inboundItemSerialRepository.existsById(serialId);
        if (exist) {
            inboundItemSerialRepository.deleteInboundSerial(serialId);
        }
    }
    @Override
    public void deleteSaler(Integer salerId) {
        Boolean exist = salerRepository.existsById(salerId);
        if (exist) {
            salerRepository.deleteSaler(salerId);
        }
    }
    @Override
    public void deleteManufacturer(Integer manufacturerId) {
        Boolean exist = manufactuterRepository.existsById(manufacturerId);
        if (exist) {
            manufactuterRepository.deleteManufacturer(manufacturerId);
        }
    }
}
