package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.IItemSerialDto;
import org.warehouse.model.inbound.InboundItemSerial;

import java.util.List;

public interface IInboundItemSerialRepository extends JpaRepository<InboundItemSerial, Integer> {
    @Query(nativeQuery = true, value = "select " +
            "s.id, s.serial from inbound_items_serials s " +
            "join inbound_items i on i.id = s.inbound_item_id " +
            "where s.inbound_item_id = :itemId")
    List<IItemSerialDto> getItemSerial(@Param("itemId") Integer itemId);
    @Modifying
    @Transactional
    @Query(value = "update InboundItemSerial i set i.serial = :serial where i.id = :id")
    void updateSerial(@Param("serial") String serial, @Param("id") Integer id);
    @Query(value = "select i.serial " +
            "from InboundItemSerial i " +
            "join i.inboundItem ib " +
            "where ib.id = :id")
    List<String> getSerial(@Param("id") Integer id);
}
