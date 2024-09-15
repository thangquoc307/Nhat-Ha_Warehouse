package org.warehouse.service;

import org.warehouse.dto.CompareDto;

import java.time.LocalDate;
import java.util.List;

public interface IDataInputService {
    List<CompareDto> getDataForCompare(LocalDate startDate, LocalDate endDate, Integer warehouseId);
}
