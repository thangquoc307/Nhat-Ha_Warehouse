package org.warehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.IOutboundItemShowDto;
import org.warehouse.dto.ItemDetailDto;
import org.warehouse.model.outbound.Outbound;

import java.time.LocalDate;
import java.util.List;

public interface IOutboundRepository extends JpaRepository<Outbound, Integer> {
    @Query(nativeQuery = true, value = "select o.id, o.note, o.partner, " +
            "o.release_date, o.image, o.so, i.description, i.part_number, " +
            "s.name, ifnull(i.count, count(i.id)) as count, m.name as manufacturer " +
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
            "and (isnull(i.is_delete) or i.is_delete = 0) " +
            "group by i.id, o.release_date, o.so, o.id " +
            "order by o.release_date desc, o.so;",
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
                    "and (isnull(i.is_delete) or i.is_delete = 0) " +
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
            "and i.isDelete = false " +
            "and i.outbound.id = :stockId")
    List<ItemDetailDto> getAllItemByStock(@Param("stockId") Integer stockId);
    @Transactional
    @Modifying
    @Query(value = "update Outbound o set o.isDelete = true where o.id = :id")
    void deleteOutbound(@Param("id") Integer id);
//    @Query(value = "select new org.warehouse.dto.StockCreateDto(" +
//            "s.id, s.releaseDate, s.so, s.partner, s.note, s.saler.id, s.warehouse.id) " +
//            "from Outbound s where s.id = :id and s.isDelete = false ")
//    StockCreateDto getStockNoteForEdit(@Param("id") Integer id);

}
