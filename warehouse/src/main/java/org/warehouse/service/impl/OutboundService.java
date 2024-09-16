package org.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.warehouse.dto.*;
import org.warehouse.model.Saler;
import org.warehouse.model.Warehouse;
import org.warehouse.model.inbound.Inbound;
import org.warehouse.model.outbound.Outbound;
import org.warehouse.repository.*;
import org.warehouse.service.IOutboundService;

import java.time.LocalDate;
import java.util.List;

@Service
public class OutboundService implements IOutboundService {
    @Autowired
    private IOutboundRepository outboundRepository;
    @Autowired
    private IInboundRepository inboundRepository;
    @Autowired
    private IWarehouseRepository warehouseRepository;
    @Autowired
    private IOutboundItemSerialRepository outboundIItemSerialRepository;
    @Autowired
    private IInboundItemSerialRepository inboundItemSerialRepository;
    @Autowired
    private ISalerRepository salerRepository;
    @Autowired
    private IManufactuterRepository manufactuterRepository;
    @Override
    public Page<IOutboundItemShowDto> getOutboundForShow(
            String search, LocalDate startDate, LocalDate endDate,
            Integer warehouseId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return outboundRepository.getItemDto(
                "%" + search + "%", startDate, endDate, warehouseId, pageable);
    }

    @Override
    public Page<IInboundItemShowDto> getInboundForShow(
            String search, LocalDate startDate, LocalDate endDate,
            Integer warehouseId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return inboundRepository.getItemDto(
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
    public List<IManufacturerDto> getAllManufacturer() {
        return manufactuterRepository.getAllManufacturer();
    }

    @Override
    public List<ItemDetailDto> getOutbound(Integer stockId) {
        List<ItemDetailDto> itemDetailDtos =
                outboundRepository.getAllItemByStock(stockId);
        itemDetailDtos.stream().forEach(itemDetailDto -> {
            List<IItemSerialDto> serialDtoList =
                    outboundIItemSerialRepository.getItemSerial(itemDetailDto.getId());
            itemDetailDto.setItemSerials(serialDtoList);
            itemDetailDto.setHasSerial(itemDetailDto.getCount() == null);
            if (itemDetailDto.getCount() == null) {
                itemDetailDto.setCount(serialDtoList.size());
            }
        });
        return itemDetailDtos;
    }

    @Override
    public List<ItemDetailDto> getInbound(Integer inboundId) {
        List<ItemDetailDto> itemDetailDtos =
                inboundRepository.getAllItemByStock(inboundId);
        itemDetailDtos.stream().forEach(itemDetailDto -> {

            List<IItemSerialDto> serialDtoList =
                    inboundItemSerialRepository.getItemSerial(itemDetailDto.getId());
            itemDetailDto.setItemSerials(serialDtoList);
            itemDetailDto.setHasSerial(itemDetailDto.getCount() == null);
            if (itemDetailDto.getCount() == null) {
                itemDetailDto.setCount(serialDtoList.size());
            }
        });
        return itemDetailDtos;
    }

    @Override
    public CreateInboundDto getInboundCreate(Integer id) {
        return inboundRepository.getInboundEdit(id);
    }

    @Override
    public void modifyInbound(CreateInboundDto createInboundDto) {
        if (createInboundDto.getId() != null) {
            inboundRepository.editInbound(
                    createInboundDto.getInboundCode(),
                    createInboundDto.getReleaseDate(),
                    createInboundDto.getLocationFrom(),
                    createInboundDto.getNote(),
                    createInboundDto.getWarehouseId(),
                    createInboundDto.getId()
            );
        } else {
            inboundRepository.save(new Inbound(
                    createInboundDto.getInboundCode(),
                    createInboundDto.getReleaseDate(),
                    createInboundDto.getLocationFrom(),
                    createInboundDto.getNote(),
                    new Warehouse(createInboundDto.getWarehouseId())
            ));
        }
    }

    @Override
    public CreateOutboundDto getOutboundCreate(Integer id) {
        return outboundRepository.getOutboundEdit(id);
    }

    @Override
    public void modifyOutbound(CreateOutboundDto createOutboundDto) {
        Integer salerId = createOutboundDto.getSalerId() == 0 ? null : createOutboundDto.getSalerId();
        if (createOutboundDto.getId() != null) {
            outboundRepository.editOutbound(
                    createOutboundDto.getSo(),
                    createOutboundDto.getReleaseDate(),
                    createOutboundDto.getPartner(),
                    createOutboundDto.getNote(),
                    salerId,
                    createOutboundDto.getWarehouseId(),
                    createOutboundDto.getId());
        } else {
            outboundRepository.save(new Outbound(
                    createOutboundDto.getReleaseDate(),
                    createOutboundDto.getSo(),
                    createOutboundDto.getPartner(),
                    createOutboundDto.getNote(),
                    createOutboundDto.getSalerId() == 0
                            ? null : new Saler(createOutboundDto.getSalerId()),
                    new Warehouse(createOutboundDto.getWarehouseId())
            ));
        }
    }

//    @Override
//    public StockCreateDto getStockCreate(Integer id) {
//        return stockNoteRepository.getStockNoteForEdit(id);
//    }
//
//    @Override
//    public void modifyStock(StockCreateDto stockCreateDto) {
//        Outbound outbound = new Outbound(
//                stockCreateDto.getId(),
//                stockCreateDto.getReleaseDate(),
//                stockCreateDto.getSo(),
//                stockCreateDto.getPartner(),
//                stockCreateDto.getNote(),
//                stockCreateDto.getSalerId() == 0 ? null :
//                        new Saler(stockCreateDto.getSalerId()),
//                new Warehouse(stockCreateDto.getWarehouseId()));
//        stockNoteRepository.save(outbound);
//    }
//
//    @Override
//    public ItemCreateDto getItemCreate(Integer id) {
//        return itemRepository.getItemForEdit(id);
//    }
//
//    @Override
//    public void modifyItem(ItemCreateDto itemCreateDto) {
//        OutboundItem item = new OutboundItem(
//                itemCreateDto.getId(),
//                itemCreateDto.getPartNumber(),
//                itemCreateDto.getDescription(),
//                itemCreateDto.isHasSerial() ? null : 0,
//                new Outbound(itemCreateDto.getStockNoteId())
//        );
//        itemRepository.save(item);
//    }

    @Override
    public void createPdf(byte[] data, Integer id, Boolean isInbound) {
        if (isInbound) {
            Inbound inbound = inboundRepository.findById(id).get();
            if (inbound != null) {
                inbound.setImage(data);
                inboundRepository.save(inbound);
            }
        } else {
            Outbound outbound = outboundRepository.findById(id).get();
            if (outbound != null) {
                outbound.setImage(data);
                outboundRepository.save(outbound);
            }
        }
    }

    @Override
    public byte[] getImage(Integer id, Boolean isInbound) {
        if (isInbound) {
            return inboundRepository
                    .findById(id)
                    .get()
                    .getImage();
        } else {
            return outboundRepository
                    .findById(id)
                    .get()
                    .getImage();
        }
    }
}
