package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.warehouse.model.Item;

public interface IItemRepository extends JpaRepository<Item, Integer> {
}
