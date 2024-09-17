package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.CompareDto;
import org.warehouse.dto.ItemCreateDto;
import org.warehouse.dto.TransferCompareDto;
import org.warehouse.dto.export.ExportItemDto;
import org.warehouse.model.inbound.InboundItem;
import org.warehouse.model.outbound.OutboundItem;

import java.time.LocalDate;
import java.util.List;

public interface IInboundItemRepository extends JpaRepository<InboundItem, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update OutboundItem i set i.count = :count where i.id = :id")
    void editCount(@Param("id") Integer id, @Param("count") Integer count);
    @Query(value = "select new org.warehouse.dto.ItemCreateDto (" +
            "i.id, i.partNumber, i.description, " +
            "(case when i.count is null then true else false end), " +
            "ib.id, m.id, 1 ) " +
            "from InboundItem i " +
            "join i.inbound ib " +
            "join i.manufacturer m " +
            "where i.id = :id")
    ItemCreateDto getItemForEdit(@Param("id") Integer id);
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
    @Transactional
    @Modifying
    @Query(value = "update inbound_items i set " +
            "i.part_number = :partNumber, " +
            "i.description = :description, " +
            "i.manufacturer_id = :manufacturerId " +
            "where i.id = :id", nativeQuery = true)
    void editItem(
            @Param("partNumber") String partNumber,
            @Param("description") String description,
            @Param("manufacturerId") Integer manufacturerId,
            @Param("id") Integer id);
    @Query(value = "select new org.warehouse.dto.export.ExportItemDto(" +
            "i.id, i.partNumber, i.description, i.count) " +
            "from InboundItem i " +
            "join i.inbound ib " +
            "where ib.id = :id")
    List<ExportItemDto> getListItem(@Param("id") Integer id);

}
