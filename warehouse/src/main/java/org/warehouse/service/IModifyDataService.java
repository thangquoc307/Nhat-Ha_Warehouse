package org.warehouse.service;

import org.warehouse.dto.CountDto;
import org.warehouse.dto.DataEditDto;
import org.warehouse.dto.EditSerialDto;

import java.util.List;

public interface IModifyDataService {
    void createWarehouse(DataEditDto dataEditDto);
    void editWarehouse(DataEditDto dataEditDto);
    void createSaler(DataEditDto dataEditDto);
    void editSaler(DataEditDto dataEditDto);
    void createSerial(Integer id, List<String> serialList, boolean isInbound);
    void editSerial(EditSerialDto editSerialDto);
    void editCount(CountDto countDto);
    void createManufacturer(DataEditDto dataEditDto);
    void editManufacturer(DataEditDto dataEditDto);
}
