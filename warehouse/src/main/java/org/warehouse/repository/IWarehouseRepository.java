package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.warehouse.dto.IWarehouseDto;
import org.warehouse.model.Warehouse;

import java.util.List;

public interface IWarehouseRepository extends JpaRepository<Warehouse, Integer> {
    @Query(nativeQuery = true, value = "select id, name from warehouses where is_delete = 0")
    List<IWarehouseDto> getAllWarehouse();
}
