package org.warehouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.warehouse.data.IItemShowDto;
import org.warehouse.model.StockNote;

public interface IStockNoteRepository extends JpaRepository<StockNote, Integer> {
    @Query(nativeQuery = true, value = "select n.note, n.partner, n.release_date, " +
            "n.so, i.description, i.part_number, s.name, ifnull(i.count, count(*)) as count " +
            "from stock_notes n " +
            "join items i on n.id = i.stock_node_id " +
            "left join salers s on n.sale_id = s.id " +
            "left join items_serials sr on i.id = sr.item_id " +
            "where i.description like :searchKey " +
            "or i.part_number like :searchKey " +
            "or sr.serial like :searchKey " +
            "or s.name like :searchKey " +
            "or n.partner like :searchKey " +
            "or n.so like :searchKey " +
            "group by i.id, n.release_date, n.so " +
            "order by n.release_date desc, n.so;",
            countQuery = "select count(*) from (" +
                    "select i.id " +
                    "from stock_notes n " +
                    "join items i on n.id = i.stock_node_id " +
                    "left join salers s on n.sale_id = s.id " +
                    "left join items_serials sr on i.id = sr.item_id " +
                    "where i.description like :searchKey " +
                    "or i.part_number like :searchKey " +
                    "or sr.serial like :searchKey " +
                    "or s.name like :searchKey " +
                    "or n.partner like :searchKey " +
                    "or n.so like :searchKey " +
                    "group by i.id, n.release_date, n.so) as TB")
    Page<IItemShowDto> getItemDto(@Param("searchKey") String searchKey, Pageable pageable);
}
