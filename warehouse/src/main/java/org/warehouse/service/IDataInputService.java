package org.warehouse.service;

import org.warehouse.dto.DataCompareDto;
import org.warehouse.model.DataInput;

import java.time.LocalDate;
import java.util.List;

public interface IDataInputService {
    void insertDataInput(List<DataInput> dataInputs);
    List<DataCompareDto> getDataForCompare(LocalDate startDate, LocalDate endDate, Integer warehouseId);
}
