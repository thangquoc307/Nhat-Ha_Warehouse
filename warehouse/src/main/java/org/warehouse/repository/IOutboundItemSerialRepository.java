package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.IItemSerialDto;
import org.warehouse.model.outbound.OutboundItemSerial;

import java.util.List;

public interface IOutboundItemSerialRepository extends JpaRepository<OutboundItemSerial, Integer> {
    @Query(nativeQuery = true, value = "select " +
            "s.id, s.serial from outbound_items_serials s " +
            "join outbound_items i on i.id = s.outbound_item_id " +
            "where s.outbound_item_id = :itemId")
    List<IItemSerialDto> getItemSerial(@Param("itemId") Integer itemId);
//    @Query(nativeQuery = true, value = "select sn.id as stockNoteId, sn.image, " +
//            "ifnull(i.count, count(s.id)) countData, " +
//            "sn.so, sn.release_date " +
//            "from items i " +
//            "left join items_serials s on i.id = s.item_id " +
//            "join stock_notes sn on sn.id = i.stock_node_id " +
//            "where part_number = :partnumber " +
//            "and sn.warehouse_id = :warehouseId " +
//            "and (isnull(:startdate) or sn.release_date >= :startdate) " +
//            "and (isnull(:enddate) or sn.release_date <= :enddate) " +
//            "and i.is_delete = 0 and sn.is_delete = 0 " +
//            "and (isnull(s.is_delete) or s.is_delete = 0) " +
//            "group by i.stock_node_id, i.count")
//    List<IDetailItemDto> getDetailByPartnumber(
//            @Param("partnumber") String partnumber,
//            @Param("warehouseId") Integer warehouseId,
//            @Param("startdate")LocalDate startDate,
//            @Param("enddate")LocalDate endDate);
    @Modifying
    @Transactional
    @Query(value = "update OutboundItemSerial i set i.serial = :serial where i.id = :id")
    void updateSerial(@Param("serial") String serial, @Param("id") Integer id);
    @Query(value = "select o.serial " +
            "from OutboundItemSerial o " +
            "join o.outboundItem ob " +
            "where ob.id = :id")
    List<String> getSerial(@Param("id") Integer id);
}
