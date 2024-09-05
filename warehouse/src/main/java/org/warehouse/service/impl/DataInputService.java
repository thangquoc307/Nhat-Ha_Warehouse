package org.warehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.warehouse.dto.DataCompareDto;
import org.warehouse.model.DataInput;
import org.warehouse.repository.IDataInputRepository;
import org.warehouse.repository.IItemSerialRepository;
import org.warehouse.service.IDataInputService;

import java.time.LocalDate;
import java.util.List;
@Service
public class DataInputService implements IDataInputService {
    @Autowired
    private IDataInputRepository dataInputRepository;
    @Autowired
    private IItemSerialRepository itemSerialRepository;
    @Override
    public void insertDataInput(List<DataInput> dataInputs) {
        dataInputRepository.deleteAll();
        dataInputRepository.saveAll(dataInputs);
    }

    @Override
    public List<DataCompareDto> getDataForCompare(LocalDate startDate, LocalDate endDate, Integer warehouseId) {
        List<String> partnumList =
                dataInputRepository.getPartNum(warehouseId, startDate, endDate);
        List<DataCompareDto> dataCompareDtos = partnumList.stream()
                .map(s -> new DataCompareDto(s)).toList();

        dataCompareDtos.forEach(dataCompareDto -> {
            dataCompareDto.setCountInput(
                    dataInputRepository.getCountInput(dataCompareDto.getPartNumber()
                ));
            dataCompareDto.setDetailItem(itemSerialRepository.getDetailByPartnumber(
                    dataCompareDto.getPartNumber(), warehouseId, startDate, endDate));
        });

        return dataCompareDtos;
    }
}
