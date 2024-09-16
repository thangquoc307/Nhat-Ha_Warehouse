package org.warehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.CreateInboundDto;
import org.warehouse.dto.IInboundItemShowDto;
import org.warehouse.dto.ItemDetailDto;
import org.warehouse.model.inbound.Inbound;

import java.time.LocalDate;
import java.util.List;

public interface IInboundRepository extends JpaRepository<Inbound, Integer> {
    @Query(nativeQuery = true, value = "select i.id, i.image, i.inbound_code, " +
            "i.location_from, i.note, i.release_date, " +
            "IFNULL(ii.count, count(ii.id)) as count, " +
            "ii.description, ii.part_number, m.name " +
            "from inbounds i " +
            "left join inbound_items ii on i.id = ii.inbound_id " +
            "left join inbound_items_serials iis on ii.id = iis.inbound_item_id " +
            "left join manufacturers m on m.id = ii.manufacturer_id " +
            "where ((isnull(i.inbound_code) OR i.inbound_code LIKE :searchKey) " +
            "OR (isnull(i.location_from) OR i.location_from LIKE :searchKey) " +
            "OR (isnull(i.note) OR i.note LIKE :searchKey) " +
            "OR (isnull(ii.description) OR ii.description LIKE :searchKey) " +
            "OR (isnull(ii.part_number) OR ii.part_number LIKE :searchKey) " +
            "OR (isnull(m.name) OR m.name LIKE :searchKey)) " +
            "AND (isnull(:startDate) OR i.release_date >= :startDate) " +
            "AND (isnull(:endDate) OR i.release_date <= :endDate) " +
            "AND i.warehouse_id = :warehouseId " +
            "AND i.is_delete = 0 " +
            "GROUP BY i.id, i.release_date, i.inbound_code, ii.id " +
            "ORDER BY i.release_date DESC, i.inbound_code;",
            countQuery = "select count(*) from (" +
                    "select i.id " +
                    "from inbounds i " +
                    "left join inbound_items ii on i.id = ii.inbound_id " +
                    "left join inbound_items_serials iis on ii.id = iis.inbound_item_id " +
                    "left join manufacturers m on m.id = ii.manufacturer_id " +
                    "where ((isnull(i.inbound_code) OR i.inbound_code LIKE :searchKey) " +
                    "OR (isnull(i.location_from) OR i.location_from LIKE :searchKey) " +
                    "OR (isnull(i.note) OR i.note LIKE :searchKey) " +
                    "OR (isnull(ii.description) OR ii.description LIKE :searchKey) " +
                    "OR (isnull(ii.part_number) OR ii.part_number LIKE :searchKey) " +
                    "OR (isnull(m.name) OR m.name LIKE :searchKey)) " +
                    "AND (isnull(:startDate) OR i.release_date >= :startDate) " +
                    "AND (isnull(:endDate) OR i.release_date <= :endDate) " +
                    "AND i.warehouse_id = :warehouseId " +
                    "AND i.is_delete = 0 " +
                    "GROUP BY i.id, i.release_date, i.inbound_code, ii.id " +
                    "ORDER BY i.release_date DESC, i.inbound_code" +
                    ") as TB")
    Page<IInboundItemShowDto> getItemDto(
            @Param("searchKey") String searchKey,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("warehouseId") Integer warehouseId,
            Pageable pageable);
    @Transactional
    @Modifying
    @Query(value = "update Inbound i set i.isDelete = true where i.id = :id")
    void deleteInbound(@Param("id") Integer id);
    @Query(value = "select new org.warehouse.dto.ItemDetailDto(" +
            "i.id, i.partNumber, i.description, i.count, m.name) " +
            "from InboundItem i " +
            "left join i.manufacturer m " +
            "where i.inbound.isDelete = false " +
            "and i.inbound.id = :stockId")
    List<ItemDetailDto> getAllItemByStock(@Param("stockId") Integer stockId);
    @Query(value = "select new org.warehouse.dto.CreateInboundDto(" +
            "i.id, i.inboundCode, i.releaseDate, i.locationFrom, i.note, w.id) " +
            "from Inbound i " +
            "left join i.warehouse w " +
            "where i.id = :id")
    CreateInboundDto getInboundEdit(@Param("id") Integer id);
    @Transactional
    @Modifying
    @Query(value = "update inbounds i set " +
            "i.inbound_code = :inboundCode, " +
            "i.release_date = :release, " +
            "i.location_from = :locationFrom, " +
            "i.note = :note, " +
            "i.warehouse_id = :warehouseId " +
            "where i.id = :id", nativeQuery = true)
    void editInbound(
            @Param("inboundCode") String inboundCode,
            @Param("release") LocalDate release,
            @Param("locationFrom") String location,
            @Param("note") String note,
            @Param("warehouseId") Integer warehouseId,
            @Param("id") Integer id);

}
