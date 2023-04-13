package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.dto.ProfesorDto;
import com.utm.msei.persistence.entity.ClasaEntity;
import com.utm.msei.persistence.entity.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClasaRepository extends JpaRepository<ClasaEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update ClasaEntity c set c.idProfesor = ?1 where c.id = ?2")
    int updateIdProfesorById(ProfesorEntity idProfesor, int id);
    ClasaEntity findByNume(String clasaName);

    @Query("select c.idProfesor from ClasaEntity c where c.nume = ?1")
    ProfesorEntity findDiriginte(String nume);

    @Query("select c from ClasaEntity c where c.nume = ?1")
    ClasaEntity getClasaForClasaName(String nume);

}
