package org.warehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.warehouse.dto.IItemShowDto;
import org.warehouse.dto.ItemDetailDto;
import org.warehouse.model.StockNote;

import java.time.LocalDate;
import java.util.List;

public interface IStockNoteRepository extends JpaRepository<StockNote, Integer> {
    @Query(nativeQuery = true, value = "select n.id, n.note, n.partner, n.release_date, " +
            "n.so, i.description, i.part_number, s.name, ifnull(i.count, count(*)) as count " +
            "from stock_notes n " +
            "join items i on n.id = i.stock_node_id " +
            "left join salers s on n.sale_id = s.id " +
            "left join items_serials sr on i.id = sr.item_id " +
            "where (i.description like :searchKey " +
            "or i.part_number like :searchKey " +
            "or sr.serial like :searchKey " +
            "or s.name like :searchKey " +
            "or n.partner like :searchKey " +
            "or n.so like :searchKey) " +
            "and (isnull(:startDate) or n.release_date >= :startDate) " +
            "and (isnull(:endDate) or n.release_date <= :endDate) " +
            "and n.warehouse_id = :warehouseId " +
            "and (n.is_delete = 0 and i.is_delete = 0) " +
            "group by i.id, n.release_date, n.so " +
            "order by n.release_date desc, n.so;",
            countQuery = "select count(*) from (" +
                    "select i.id " +
                    "from stock_notes n " +
                    "join items i on n.id = i.stock_node_id " +
                    "left join salers s on n.sale_id = s.id " +
                    "left join items_serials sr on i.id = sr.item_id " +
                    "where (i.description like :searchKey " +
                    "or i.part_number like :searchKey " +
                    "or sr.serial like :searchKey " +
                    "or s.name like :searchKey " +
                    "or n.partner like :searchKey " +
                    "or n.so like :searchKey) " +
                    "and (isnull(:startDate) or n.release_date >= :startDate) " +
                    "and (isnull(:endDate) or n.release_date <= :endDate) " +
                    "and n.warehouse_id = :warehouseId " +
                    "and (n.is_delete = 0 and i.is_delete = 0) " +
                    "group by i.id, n.release_date, n.so) as TB")
    Page<IItemShowDto> getItemDto(
            @Param("searchKey") String searchKey,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("warehouseId") Integer warehouseId,
            Pageable pageable);

    @Query(value = "select new org.warehouse.dto.ItemDetailDto(" +
            "i.id, i.partNumber, i.description, i.count) " +
            "from Item i where i.stockNote.isDelete = false and i.stockNote.id = :stockId")
    List<ItemDetailDto> getAllItemByStock(@Param("stockId") Integer stockId);

}
