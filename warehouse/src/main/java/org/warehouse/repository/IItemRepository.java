package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.model.Item;

public interface IItemRepository extends JpaRepository<Item, Integer> {
    @Query(value = "select new Item (i.id) from Item i " +
            "where i.isDelete = false and i.id = :id")
    Item getItemById(@Param("id") Integer id);
    @Transactional
    @Modifying
    @Query(value = "update Item i set i.count = :count where i.id = :id")
    void editCount(@Param("id") Integer id, @Param("count") Integer count);
}
