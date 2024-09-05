package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.warehouse.dto.ISalerDto;
import org.warehouse.model.Saler;

import java.util.List;

public interface ISalerRepository extends JpaRepository<Saler, Integer> {
    @Query(nativeQuery = true,
            value = "select s.id, s.name from salers s " +
                    "where s.is_delete = 0 " +
                    "order by id desc ")
    List<ISalerDto> getAllSaler();
}
