package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.CompareDto;
import org.warehouse.dto.ItemCreateDto;
import org.warehouse.dto.TransferCompareDto;
import org.warehouse.model.outbound.OutboundItem;

import java.time.LocalDate;
import java.util.List;

public interface IOutboundItemRepository extends JpaRepository<OutboundItem, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update OutboundItem i set i.count = :count where i.id = :id")
    void editCount(@Param("id") Integer id, @Param("count") Integer count);
    @Query(value = "select new org.warehouse.dto.ItemCreateDto (" +
            "o.id, o.partNumber, o.description, " +
            "(case when o.count is null then true else false end), " +
            "ob.id, m.id, 0 ) " +
            "from OutboundItem o " +
            "join o.outbound ob " +
            "join o.manufacturer m " +
            "where o.id = :id")
    ItemCreateDto getItemForEdit(@Param("id") Integer id);
    @Query(value = "select new org.warehouse.dto.TransferCompareDto(" +
            "ob.id, ob.so, o.partNumber, o.description, " +
            "ifnull(o.count, count(os.id)), m.name, " +
            "ob.releaseDate, " +
            "(case when ob.image is null then false else true end)) " +
            "from OutboundItem o " +
            "join o.outbound ob " +
            "left join o.outboundItemSerials os " +
            "left join o.manufacturer m " +
            "where (:startDate is null or :startDate <= ob.releaseDate) " +
            "and (:endDate is null or :endDate >= ob.releaseDate) " +
            "and ob.isDelete = false " +
            "and ob.warehouse.id = :warehouseId " +
            "group by o.id, ob.id, ob.so, o.partNumber, " +
            "o.description, o.count, m.name, ob.releaseDate, ob.image")
    List<TransferCompareDto> getDataCompare(
            @Param("startDate") LocalDate startDate,
            @Param("endDate")LocalDate endDate,
            @Param("warehouseId")Integer warehouseId);
    @Query(value = "select ifnull(o.count, count(os.serial)) " +
            "from OutboundItem o " +
            "left join o.outboundItemSerials os " +
            "join o.outbound ob " +
            "where o.partNumber = :partNumber " +
            "and ob.releaseDate < :startDate " +
            "group by o.id")
    List<Integer> countOutboundItem(
            @Param("partNumber") String partNumber,
            @Param("startDate") LocalDate startEnd);
    @Transactional
    @Modifying
    @Query(value = "update outbound_items o set " +
            "o.part_number = :partNumber, " +
            "o.description = :description, " +
            "o.manufacturer_id = :manufacturerId " +
            "where o.id = :id", nativeQuery = true)
    void editItem(
            @Param("partNumber") String partNumber,
            @Param("description") String description,
            @Param("manufacturerId") Integer manufacturerId,
            @Param("id") Integer id);
}
