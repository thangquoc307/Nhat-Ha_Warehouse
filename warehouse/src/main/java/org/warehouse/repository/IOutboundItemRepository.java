package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.model.outbound.OutboundItem;

public interface IOutboundItemRepository extends JpaRepository<OutboundItem, Integer> {
//    @Query(value = "select new OutboundItem (i.id) from OutboundItem i " +
//            "where i.isDelete = false and i.id = :id")
//    OutboundItem getItemById(@Param("id") Integer id);
//    @Transactional
//    @Modifying
//    @Query(value = "update OutboundItem i set i.count = :count where i.id = :id")
//    void editCount(@Param("id") Integer id, @Param("count") Integer count);
//    @Query(value = "select new org.warehouse.dto.ItemCreateDto(" +
//            "i.id, i.partNumber, i.description, i.count, i.stockNote.id) " +
//            "from OutboundItem i where i.isDelete = false and i.id = :id")
//    ItemCreateDto getItemForEdit(@Param("id") Integer id);
    @Transactional
    @Modifying
    @Query(value = "update OutboundItem o set o.isDelete = true where o.id = :id")
    void deleteOutboundItem(@Param("id") Integer id);
}
