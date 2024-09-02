package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.warehouse.dto.IItemSerialDto;
import org.warehouse.model.ItemSerial;

import java.util.List;

public interface IItemSerialRepository extends JpaRepository<ItemSerial, Integer> {
    @Query(nativeQuery = true, value = "select " +
            "s.id, s.serial, st.name status from items_serials s " +
            "join items i on i.id = s.item_id " +
            "join status st on st.id = s.status_id " +
            "where i.is_delete = 0 and s.item_id = :itemId")
    List<IItemSerialDto> getItemSerial(@Param("itemId") Integer itemId);
}
