package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.CompareDto;
import org.warehouse.dto.TransferCompareDto;
import org.warehouse.model.inbound.InboundItem;
import org.warehouse.model.outbound.OutboundItem;

import java.time.LocalDate;
import java.util.List;

public interface IInboundItemRepository extends JpaRepository<InboundItem, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update OutboundItem i set i.count = :count where i.id = :id")
    void editCount(@Param("id") Integer id, @Param("count") Integer count);
//    @Query(value = "select new org.warehouse.dto.ItemCreateDto(" +
//            "i.id, i.partNumber, i.description, i.count, i.stockNote.id) " +
//            "from OutboundItem i where i.isDelete = false and i.id = :id")
//    ItemCreateDto getItemForEdit(@Param("id") Integer id);
    @Query(value = "select new org.warehouse.dto.TransferCompareDto(" +
            "ib.id, ib.inboundCode, i.partNumber, i.description, " +
            "ifnull(i.count, count(ii.id)), m.name, " +
            "ib.releaseDate, " +
            "(case when ib.image is null then false else true end )) " +
            "from InboundItem i " +
            "join i.inbound ib " +
            "left join i.inboundItemSerials ii " +
            "left join i.manufacturer m " +
            "where (:startDate is null  or :startDate <= ib.releaseDate) " +
            "and (:endDate is null or :endDate >= ib.releaseDate) " +
            "and ib.isDelete = false " +
            "and ib.warehouse.id = :warehouseId " +
            "group by i.id, ib.id, ib.inboundCode, i.partNumber, " +
            "i.description, i.count, m.name, ib.releaseDate, ib.image")
    List<TransferCompareDto> getDataCompare(
            @Param("startDate")LocalDate startDate,
            @Param("endDate")LocalDate endDate,
            @Param("warehouseId")Integer warehouseId);
    @Query(value = "select ifnull(i.count, count(ii.serial)) " +
            "from InboundItem i " +
            "left join i.inboundItemSerials ii " +
            "join i.inbound ib " +
            "where i.partNumber = :partNumber " +
            "and ib.releaseDate < :startDate " +
            "group by i.id")
    List<Integer> countInboundItem(
            @Param("partNumber") String partNumber,
            @Param("startDate") LocalDate startEnd);
}
