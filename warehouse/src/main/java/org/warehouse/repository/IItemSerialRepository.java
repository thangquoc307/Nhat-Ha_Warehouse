package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.warehouse.dto.IDetailItemDto;
import org.warehouse.dto.IItemSerialDto;
import org.warehouse.model.ItemSerial;

import java.time.LocalDate;
import java.util.List;

public interface IItemSerialRepository extends JpaRepository<ItemSerial, Integer> {
    @Query(nativeQuery = true, value = "select " +
            "s.id, s.serial from items_serials s " +
            "join items i on i.id = s.item_id " +
            "where i.is_delete = 0 and s.is_delete = 0 and s.item_id = :itemId")
    List<IItemSerialDto> getItemSerial(@Param("itemId") Integer itemId);
    @Query(nativeQuery = true, value = "select " +
            "ifnull(i.count, count(s.id)) countData, " +
            "sn.so, sn.release_date " +
            "from items i " +
            "left join items_serials s on i.id = s.item_id " +
            "join stock_notes sn on sn.id = i.stock_node_id " +
            "where part_number = :partnumber " +
            "and sn.warehouse_id = :warehouseId " +
            "and (isnull(:startdate) or sn.release_date >= :startdate) " +
            "and (isnull(:enddate) or sn.release_date <= :enddate) " +
            "and i.is_delete = 0 and sn.is_delete = 0 " +
            "and (isnull(s.is_delete) or s.is_delete = 0) " +
            "group by i.stock_node_id, i.count")
    List<IDetailItemDto> getDetailByPartnumber(
            @Param("partnumber") String partnumber,
            @Param("warehouseId") Integer warehouseId,
            @Param("startdate")LocalDate startDate,
            @Param("enddate")LocalDate endDate);
}
