package org.warehouse.service;

import org.warehouse.dto.CountDto;
import org.warehouse.dto.DataEditDto;

import java.util.List;

public interface IModifyDataService {
    void createWarehouse(DataEditDto dataEditDto);
    void editWarehouse(DataEditDto dataEditDto);
    void createSaler(DataEditDto dataEditDto);
    void editSaler(DataEditDto dataEditDto);
    void createSerial(Integer id, List<String> serialList);
    void editSerial(DataEditDto dataEditDto);
    void editCount(CountDto countDto);
}
