package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.ElevEntity;
import com.utm.msei.persistence.entity.MamaEntity;
import com.utm.msei.persistence.entity.TataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ElevRepository extends JpaRepository<ElevEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update ElevEntity e set e.idTata = ?1 where e.id = ?2")
    int updateIdTataById(TataEntity idTata, int id);

    @Transactional
    @Modifying
    @Query("update ElevEntity e set e.idMama = ?1 where e.id = ?2")
    int updateIdMamaById(MamaEntity idMama, int id);

    List<ElevEntity> findByIdClasaOrderByIdUser_NumeAscIdUser_PrenumeAsc(Integer idClasa);
}
