package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.warehouse.model.DataInput;

import java.time.LocalDate;
import java.util.List;

public interface IDataInputRepository extends JpaRepository<DataInput, Integer> {
//    @Query(nativeQuery = true, value = "select part_number from outbound_items i " +
//            "join outbounds o on o.id = i.outbounds_id " +
//            "where i.is_delete = 0 and o.is_delete = 0 " +
//            "and o.warehouse_id = :warehouseId " +
//            "and (isnull(:startDate) or o.release_date >= :startDate) " +
//            "and (isnull(:endDate) or o.release_date <= :endDate) " +
//            "group by part_number " +
//            "union " +
//            "select part_number from data_input group by part_number")
//    List<String> getPartNum(
//            @Param("warehouseId") Integer warehouseId,
//            @Param("startDate")LocalDate startDate,
//            @Param("endDate")LocalDate endDate);
//    @Query(nativeQuery = true, value = "select d.count from data_input d " +
//            "where d.part_number = :partnum")
//    Integer getCountInput(@Param("partnum") String partnum);
}
