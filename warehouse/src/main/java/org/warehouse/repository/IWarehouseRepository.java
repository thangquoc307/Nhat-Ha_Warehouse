package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.IWarehouseDto;
import org.warehouse.model.Warehouse;

import java.util.List;

public interface IWarehouseRepository extends JpaRepository<Warehouse, Integer> {
    @Query(nativeQuery = true,
            value = "select id, name from warehouses " +
                    "where is_delete = 0 " +
                    "order by id desc ")
    List<IWarehouseDto> getAllWarehouse();
    @Query(value = "select new Warehouse (w.id, w.name, w.isDelete) " +
            "from Warehouse w where w.id = :id and w.isDelete = false")
    Warehouse findWarehouse(@Param("id") Integer id);
    @Transactional
    @Modifying
    @Query(value = "update Warehouse w set w.isDelete = true where w.id = :id")
    void deleteWarehouse(@Param("id") Integer id);

}
