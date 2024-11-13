package org.warehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.CreateOutboundDto;
import org.warehouse.dto.IOutboundItemShowDto;
import org.warehouse.dto.ItemDetailDto;
import org.warehouse.dto.export.ExportStockDto;
import org.warehouse.model.outbound.Outbound;

import java.time.LocalDate;
import java.util.List;

public interface IOutboundRepository extends JpaRepository<Outbound, Integer> {
    @Query(nativeQuery = true, value = "select o.id, o.note, o.partner, " +
            "o.release_date, o.image, o.so, i.description, i.part_number, " +
            "s.name, ifnull(i.count, count(sr.id)) as count, m.name as manufacturer " +
            "from outbounds o " +
            "left join outbound_items i on o.id = i.outbound_id " +
            "left join salers s on o.sale_id = s.id " +
            "left join outbound_items_serials sr on i.id = sr.outbound_item_id " +
            "left join manufacturers m on i.manufacturer_id = m.id " +
            "where ((isnull(i.description) or i.description like :searchKey) " +
            "or (isnull(i.part_number) or i.part_number like :searchKey) " +
            "or (isnull(sr.serial) or sr.serial like :searchKey) " +
            "or (isnull(s.name) or s.name like :searchKey) " +
            "or (isnull(o.partner) or o.partner like :searchKey) " +
            "or (isnull(o.so) or o.so like :searchKey)) " +
            "and (isnull(:startDate) or o.release_date >= :startDate) " +
            "and (isnull(:endDate) or o.release_date <= :endDate) " +
            "and o.warehouse_id = :warehouseId " +
            "and o.is_delete = 0 " +
            "group by i.id, o.release_date, o.so, o.id " +
            "order by o.id, o.release_date desc, o.so;",
            countQuery = "select count(*) from (" +
                    "select i.id " +
                    "from outbounds o " +
                    "left join outbound_items i on o.id = i.outbound_id " +
                    "left join salers s on o.sale_id = s.id " +
                    "left join outbound_items_serials sr on i.id = sr.outbound_item_id " +
                    "left join manufacturers m on i.manufacturer_id = m.id " +
                    "where ((isnull(i.description) or i.description like :searchKey) " +
                    "or (isnull(i.part_number) or i.part_number like :searchKey) " +
                    "or (isnull(sr.serial) or sr.serial like :searchKey) " +
                    "or (isnull(s.name) or s.name like :searchKey) " +
                    "or (isnull(o.partner) or o.partner like :searchKey) " +
                    "or (isnull(o.so) or o.so like :searchKey)) " +
                    "and (isnull(:startDate) or o.release_date >= :startDate) " +
                    "and (isnull(:endDate) or o.release_date <= :endDate) " +
                    "and o.warehouse_id = :warehouseId " +
                    "and o.is_delete = 0 " +
                    "group by i.id, o.release_date, o.so, o.id) as TB")
    Page<IOutboundItemShowDto> getItemDto(
            @Param("searchKey") String searchKey,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("warehouseId") Integer warehouseId,
            Pageable pageable);

    @Query(value = "select new org.warehouse.dto.ItemDetailDto(" +
            "i.id, i.partNumber, i.description, i.count, m.name) " +
            "from OutboundItem i " +
            "left join i.manufacturer m " +
            "where i.outbound.isDelete = false " +
            "and i.outbound.id = :stockId")
    List<ItemDetailDto> getAllItemByStock(@Param("stockId") Integer stockId);
    @Transactional
    @Modifying
    @Query(value = "update Outbound o set o.isDelete = true where o.id = :id")
    void deleteOutbound(@Param("id") Integer id);
    @Query(value = "select new org.warehouse.dto.CreateOutboundDto(" +
            "s.id, s.so, s.releaseDate, s.partner, s.note, sl.id, w.id) " +
            "from Outbound s " +
            "left join s.warehouse w " +
            "left join s.saler sl " +
            "where s.id = :id and s.isDelete = false ")
    CreateOutboundDto getOutboundEdit(@Param("id") Integer id);
    @Transactional
    @Modifying
    @Query(value = "update outbounds o set " +
            "o.so = :so, " +
            "o.release_date = :release, " +
            "o.partner = :partner, " +
            "o.note = :note, " +
            "o.sale_id = :salerId, " +
            "o.warehouse_id = :warehouseId " +
            "where o.id = :id", nativeQuery = true)
    void editOutbound(
            @Param("so") String so,
            @Param("release") LocalDate release,
            @Param("partner") String partner,
            @Param("note") String note,
            @Param("salerId") Integer salerId,
            @Param("warehouseId") Integer warehouseId,
            @Param("id") Integer id);
    @Query(value = "select new org.warehouse.dto.export.ExportStockDto(" +
            "o.id, o.releaseDate, o.so, o.partner, s.name, o.note) " +
            "from Outbound o " +
            "join o.warehouse w " +
            "left join o.saler s " +
            "where (:startDate is null or o.releaseDate >= :startDate) " +
            "and (:endDate is null or o.releaseDate <= :endDate) " +
            "and o.isDelete = false " +
            "and w.id = :id " +
            "order by o.releaseDate ")
    List<ExportStockDto> getOutboundExport(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("id") Integer id);
}
