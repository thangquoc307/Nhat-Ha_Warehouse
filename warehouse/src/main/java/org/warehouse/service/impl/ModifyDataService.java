package org.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.warehouse.dto.CountDto;
import org.warehouse.dto.DataEditDto;
import org.warehouse.dto.EditSerialDto;
import org.warehouse.model.Manufacturer;
import org.warehouse.model.Saler;
import org.warehouse.model.Warehouse;
import org.warehouse.model.inbound.InboundItem;
import org.warehouse.model.inbound.InboundItemSerial;
import org.warehouse.model.outbound.OutboundItem;
import org.warehouse.model.outbound.OutboundItemSerial;
import org.warehouse.repository.*;
import org.warehouse.service.IModifyDataService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModifyDataService implements IModifyDataService {
    @Autowired
    private ISalerRepository salerRepository;
    @Autowired
    private IWarehouseRepository warehouseRepository;
    @Autowired
    private IManufactuterRepository manufactuterRepository;
    @Autowired
    private IInboundItemRepository inboundItemRepository;
    @Autowired
    private IOutboundItemRepository outboundItemRepository;
    @Autowired
    private IInboundItemSerialRepository inboundItemSerialRepository;
    @Autowired
    private IOutboundItemSerialRepository outboundItemSerialRepository;

    @Override
    public void createWarehouse(DataEditDto dataEditDto) {
        Warehouse warehouse = new Warehouse(dataEditDto.getName());
        warehouseRepository.save(warehouse);
    }

    @Override
    public void editWarehouse(DataEditDto dataEditDto) {
        Warehouse warehouse = warehouseRepository
                .findWarehouse(dataEditDto.getId());
        if (warehouse != null) {
            warehouse.setName(dataEditDto.getName());
            warehouseRepository.save(warehouse);
        }
    }

    @Override
    public void createSaler(DataEditDto dataEditDto) {
        Saler saler = new Saler(dataEditDto.getName());
        salerRepository.save(saler);
    }

    @Override
    public void createManufacturer(DataEditDto dataEditDto) {
        Manufacturer manufacturer = new Manufacturer(dataEditDto.getName());
        manufactuterRepository.save(manufacturer);
    }

    @Override
    public void editManufacturer(DataEditDto dataEditDto) {
        Manufacturer manufacturer = manufactuterRepository
                .findManufacturer(dataEditDto.getId());
        if (manufacturer != null) {
            manufacturer.setName(dataEditDto.getName());
            manufactuterRepository.save(manufacturer);
        }

    }

    @Override
    public void editSaler(DataEditDto dataEditDto) {
        Saler saler = salerRepository
                .findSaler(dataEditDto.getId());
        if (saler != null) {
            saler.setName(dataEditDto.getName());
            salerRepository.save(saler);
        }
    }

    @Override
    public void editCount(CountDto countDto) {
        if (countDto.getIsInbound()) {
            Boolean exist = inboundItemRepository.existsById(countDto.getId());
            if (exist) {
                inboundItemRepository.editCount(countDto.getId(), countDto.getCount());
            }
        } else {
            Boolean exist = outboundItemRepository.existsById(countDto.getId());
            if (exist) {
                outboundItemRepository.editCount(countDto.getId(), countDto.getCount());
            }
        }
    }

    @Override
    public void createSerial(Integer id, List<String> serialList, boolean isInbound) {
        if (isInbound) {
            InboundItem item = inboundItemRepository.findById(id).get();
            if (item != null) {
                List<InboundItemSerial> serials = serialList.stream()
                        .map(s -> new InboundItemSerial(s, item))
                        .collect(Collectors.toList());
                inboundItemSerialRepository.saveAll(serials);
            }
        } else {
            OutboundItem item = outboundItemRepository.findById(id).get();
            if (item != null) {
                List<OutboundItemSerial> serials = serialList.stream()
                        .map(s -> new OutboundItemSerial(s, item))
                        .collect(Collectors.toList());
                outboundItemSerialRepository.saveAll(serials);
            }
        }

    }

    @Override
    public void editSerial(EditSerialDto editSerialDto) {
        if (editSerialDto.getIsInbound()) {
            Boolean exist = inboundItemSerialRepository
                    .existsById(editSerialDto.getId());
            if (exist) {
                inboundItemSerialRepository.updateSerial(
                        editSerialDto.getName(), editSerialDto.getId());
            }
        } else {
            Boolean exist = outboundItemSerialRepository
                    .existsById(editSerialDto.getId());
            if (exist) {
                outboundItemSerialRepository.updateSerial(
                        editSerialDto.getName(), editSerialDto.getId());
            }
        }
    }
}
