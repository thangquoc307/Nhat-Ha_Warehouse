package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.IManufacturerDto;
import org.warehouse.dto.ISalerDto;
import org.warehouse.model.Manufacturer;
import org.warehouse.model.Saler;

import java.util.List;

public interface IManufactuterRepository extends JpaRepository<Manufacturer, Integer> {
    @Query(nativeQuery = true,
            value = "select m.id, m.name from manufacturers m " +
                    "where m.is_delete = 0 " +
                    "order by id desc ")
    List<IManufacturerDto> getAllManufacturer();
//    @Query(value = "select new Manufacturer (m.id, m.name, m.isDelete) " +
//            "from Manufacturer m where m.id = :id and m.isDelete = false ")
//    Manufacturer findManufacturer(@Param("id") Integer id);
    @Transactional
    @Modifying
    @Query(value = "update Manufacturer m set m.isDelete = true where m.id = :id")
    void deleteManufacturer(@Param("id") Integer id);
}
