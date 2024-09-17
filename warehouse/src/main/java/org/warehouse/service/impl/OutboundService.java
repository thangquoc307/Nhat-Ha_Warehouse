package org.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.warehouse.dto.*;
import org.warehouse.dto.export.ExportItemDto;
import org.warehouse.dto.export.ExportStockDto;
import org.warehouse.model.Manufacturer;
import org.warehouse.model.Saler;
import org.warehouse.model.Warehouse;
import org.warehouse.model.inbound.Inbound;
import org.warehouse.model.inbound.InboundItem;
import org.warehouse.model.outbound.Outbound;
import org.warehouse.model.outbound.OutboundItem;
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
    @Autowired
    private IInboundItemRepository inboundItemRepository;
    @Autowired
    private IOutboundItemRepository outboundItemRepository;
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

    @Override
    public ItemCreateDto getItemCreate(Integer id, boolean isInbound) {
        if (isInbound) {
            return inboundItemRepository.getItemForEdit(id);
        } else {
            return outboundItemRepository.getItemForEdit(id);
        }
    }

    @Override
    public void modifyItem(ItemCreateDto itemCreateDto) {
        if (itemCreateDto.getId() != null) {
            if (itemCreateDto.getIsInbound() == 1) {
                inboundItemRepository.editItem(
                        itemCreateDto.getPartNumber(),
                        itemCreateDto.getDescription(),
                        itemCreateDto.getManufacturerId(),
                        itemCreateDto.getId());
            } else {
                outboundItemRepository.editItem(
                        itemCreateDto.getPartNumber(),
                        itemCreateDto.getDescription(),
                        itemCreateDto.getManufacturerId(),
                        itemCreateDto.getId());
            }
        } else {
            if (itemCreateDto.getIsInbound() == 1) {
                InboundItem inboundItem = new InboundItem(
                        itemCreateDto.getPartNumber(),
                        itemCreateDto.getDescription(),
                        itemCreateDto.isHasSerial() ? null : 0,
                        new Inbound(itemCreateDto.getStockId()),
                        new Manufacturer(itemCreateDto.getManufacturerId()));
                inboundItemRepository.save(inboundItem);
            } else {
                OutboundItem outboundItem = new OutboundItem(
                        itemCreateDto.getPartNumber(),
                        itemCreateDto.getDescription(),
                        itemCreateDto.isHasSerial() ? null : 0,
                        new Outbound(itemCreateDto.getStockId()),
                        new Manufacturer(itemCreateDto.getManufacturerId()));
                outboundItemRepository.save(outboundItem);
            }
        }
    }

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

    @Override
    public List<ExportStockDto> getDataForExport(LocalDate startDate, LocalDate endDate, Integer warehouseId, Boolean isInbound) {
        List<ExportStockDto> list = isInbound
                ? inboundRepository.getInboundExport(startDate, endDate, warehouseId)
                : outboundRepository.getOutboundExport(startDate, endDate, warehouseId);
        list.stream().forEach(exportStockDto -> {
            List<ExportItemDto> itemDtos = isInbound
                    ? inboundItemRepository.getListItem(exportStockDto.getId())
                    : outboundItemRepository.getListItem(exportStockDto.getId());
            itemDtos.stream().forEach(exportItemDto -> {
                if (exportItemDto.getCount() == null) {
                    List<String> serial = isInbound
                            ? inboundItemSerialRepository.getSerial(exportItemDto.getId())
                            : outboundIItemSerialRepository.getSerial(exportItemDto.getId());
                    exportItemDto.setSerials(serial);
                    exportItemDto.setCount(serial.size());
                }
            });
            exportStockDto.setItemDtos(itemDtos);
        });
        return list;
    }
}
