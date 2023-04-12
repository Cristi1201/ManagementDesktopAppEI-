package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.dto.*;
import com.utm.msei.persistence.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrarRepository extends JpaRepository<OrarEntity, Integer> {
    List<OrarEntity> findByIdClasa_Nume(String nume);

    @Query("select o from OrarEntity o where o.idZi = ?1 and o.idDurata = ?2 and o.idDiscProf.idProfesor = ?3")
    OrarEntity findIfProfIsOcupied(ZiSaptamanaEntity idZi, DurataLectieEntity idDurata, ProfesorEntity idProfesor);



    @Transactional
    @Modifying
    @Query("update OrarEntity o set o.idDiscProf = ?1 where o.idClasa = ?2 and o.idZi = ?3 and o.idDurata = ?4")
    int updateDiscProfByClasaZiDurata(DisciplinaProfesorEntity idDiscProf, ClasaEntity idClasa, ZiSaptamanaEntity idZi, DurataLectieEntity idDurata);

    @Override
    void deleteById(Integer integer);
}
