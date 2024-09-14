//package org.warehouse.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.warehouse.dto.CountDto;
//import org.warehouse.dto.DataEditDto;
//import org.warehouse.model.outbound.OutboundItem;
//import org.warehouse.model.outbound.OutboundItemSerial;
//import org.warehouse.model.Saler;
//import org.warehouse.model.Warehouse;
//import org.warehouse.repository.IItemRepository;
//import org.warehouse.repository.IItemSerialRepository;
//import org.warehouse.repository.ISalerRepository;
//import org.warehouse.repository.IWarehouseRepository;
//import org.warehouse.service.IModifyDataService;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ModifyDataService implements IModifyDataService {
//    @Autowired
//    private ISalerRepository salerRepository;
//    @Autowired
//    private IWarehouseRepository warehouseRepository;
//    @Autowired
//    private IItemSerialRepository itemSerialRepository;
//    @Autowired
//    private IItemRepository itemRepository;
//    @Override
//    public void createWarehouse(DataEditDto dataEditDto) {
//        Warehouse warehouse = new Warehouse(dataEditDto.getName());
//        warehouseRepository.save(warehouse);
//    }
//
//    @Override
//    public void editWarehouse(DataEditDto dataEditDto) {
//        Warehouse warehouse = warehouseRepository
//                .findWarehouse(dataEditDto.getId());
//        if (warehouse != null) {
//            warehouse.setName(dataEditDto.getName());
//            warehouseRepository.save(warehouse);
//        }
//    }
//
//    @Override
//    public void createSaler(DataEditDto dataEditDto) {
//        Saler saler = new Saler(dataEditDto.getName());
//        salerRepository.save(saler);
//    }
//
//    @Override
//    public void editSaler(DataEditDto dataEditDto) {
//        Saler saler = salerRepository
//                .findSaler(dataEditDto.getId());
//        if (saler != null) {
//            saler.setName(dataEditDto.getName());
//            salerRepository.save(saler);
//        }
//    }
//
//    @Override
//    public void createSerial(Integer id, List<String> serialList) {
//        OutboundItem item = itemRepository.getItemById(id);
//        if (item != null) {
//            List<OutboundItemSerial> serials = serialList.stream()
//                    .map(s -> new OutboundItemSerial(s, item))
//                    .collect(Collectors.toList());
//            itemSerialRepository.saveAll(serials);
//        }
//    }
//
//    @Override
//    public void editSerial(DataEditDto dataEditDto) {
//        OutboundItemSerial outboundItemSerial = itemSerialRepository
//                .findSerial(dataEditDto.getId());
//        if (outboundItemSerial != null) {
//            itemSerialRepository.updateSerial(
//                    dataEditDto.getName(), dataEditDto.getId());
//        }
//    }
//
//    @Override
//    public void editCount(CountDto countDto) {
//        OutboundItem item = itemRepository.getItemById(countDto.getId());
//        if (item != null) {
//            itemRepository.editCount(countDto.getId(), countDto.getCount());
//        }
//    }
//}
