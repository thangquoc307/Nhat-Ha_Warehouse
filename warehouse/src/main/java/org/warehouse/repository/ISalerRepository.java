package org.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.warehouse.dto.ISalerDto;
import org.warehouse.model.Saler;

import java.util.List;

public interface ISalerRepository extends JpaRepository<Saler, Integer> {
    @Query(nativeQuery = true,
            value = "select s.id, s.name from salers s " +
                    "where s.is_delete = 0 " +
                    "order by id desc ")
    List<ISalerDto> getAllSaler();
//    @Query(value = "select new Saler (s.id, s.name, s.isDelete) " +
//            "from Saler s where s.id = :id and s.isDelete = false ")
//    Saler findSaler(@Param("id") Integer id);
    @Transactional
    @Modifying
    @Query(value = "update Saler s set s.isDelete = true where s.id = :id")
    void deleteSaler(@Param("id") Integer id);
}
