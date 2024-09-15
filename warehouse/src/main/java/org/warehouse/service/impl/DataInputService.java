package org.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.warehouse.dto.CompareDto;
import org.warehouse.dto.GetImageDto;
import org.warehouse.dto.TransferCompareDto;
import org.warehouse.repository.IInboundItemRepository;
import org.warehouse.repository.IOutboundItemRepository;
import org.warehouse.service.IDataInputService;

import java.time.LocalDate;
import java.util.*;

@Service
public class DataInputService implements IDataInputService {
    @Autowired
    private IInboundItemRepository inboundItemRepository;
    @Autowired
    private IOutboundItemRepository outboundItemRepository;
    @Override
    public List<CompareDto> getDataForCompare(
            LocalDate startDate, LocalDate endDate, Integer warehouseId) {
        List<CompareDto> result = new ArrayList<>();
        List<TransferCompareDto> inboundDtos =
                inboundItemRepository.getDataCompare(startDate, endDate, warehouseId);
        List<TransferCompareDto> outboundDtos =
                outboundItemRepository.getDataCompare(startDate, endDate, warehouseId);

        inboundDtos.stream().forEach(transferCompareDto -> {
            int index = result.indexOf(new CompareDto(transferCompareDto.getPartNumber()));
            if (index != -1) {
                CompareDto compareDto = result.get(index);
                compareDto.setInboundCount(
                        compareDto.getInboundCount() + transferCompareDto.getCount());
                compareDto.getInboundList()
                        .add(new GetImageDto(
                                transferCompareDto.getIdGet(),
                                true,
                                transferCompareDto.getCode(),
                                transferCompareDto.getReleaseDate(),
                                transferCompareDto.getHasImage(),
                                transferCompareDto.getCount()));
            } else {
                CompareDto compareDto = new CompareDto();
                compareDto.setPartNumber(transferCompareDto.getPartNumber());
                compareDto.setDescription(transferCompareDto.getDescription());
                compareDto.setInboundCount(transferCompareDto.getCount());
                compareDto.setManufacturer(transferCompareDto.getManufacturer());
                compareDto.getInboundList().add(new GetImageDto(
                        transferCompareDto.getIdGet(),
                        true,
                        transferCompareDto.getCode(),
                        transferCompareDto.getReleaseDate(),
                        transferCompareDto.getHasImage(),
                        transferCompareDto.getCount()));
                result.add(compareDto);
            }
        });
        outboundDtos.stream().forEach(transferCompareDto -> {
            int index = result.indexOf(new CompareDto(transferCompareDto.getPartNumber()));
            if (index != -1) {
                CompareDto compareDto = result.get(index);
                compareDto.setOutboundCount(
                        compareDto.getOutboundCount() + transferCompareDto.getCount());
                compareDto.getOutboundList()
                        .add(new GetImageDto(
                                transferCompareDto.getIdGet(),
                                false,
                                transferCompareDto.getCode(),
                                transferCompareDto.getReleaseDate(),
                                transferCompareDto.getHasImage(),
                                transferCompareDto.getCount()));
            } else {
                CompareDto compareDto = new CompareDto();
                compareDto.setPartNumber(transferCompareDto.getPartNumber());
                compareDto.setDescription(transferCompareDto.getDescription());
                compareDto.setOutboundCount(transferCompareDto.getCount());
                compareDto.setManufacturer(transferCompareDto.getManufacturer());
                compareDto.getOutboundList().add(new GetImageDto(
                        transferCompareDto.getIdGet(),
                        false,
                        transferCompareDto.getCode(),
                        transferCompareDto.getReleaseDate(),
                        transferCompareDto.getHasImage(),
                        transferCompareDto.getCount()));
                result.add(compareDto);
            }
        });
        if (startDate != null) {
            result.stream().forEach(compareDto -> {
                Integer outboundCount = outboundItemRepository
                        .countOutboundItem(compareDto.getPartNumber(), startDate)
                        .stream().reduce(0, Integer::sum);
                Integer inboundCount = inboundItemRepository
                        .countInboundItem(compareDto.getPartNumber(), startDate)
                        .stream().reduce(0, Integer::sum);
                compareDto.setStock(inboundCount - outboundCount);
            });
        }
        result.sort(Comparator.comparing(CompareDto::getManufacturer));
        return result;
    }
}
