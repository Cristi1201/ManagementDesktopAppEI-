package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.DisciplinaEntity;
import com.utm.msei.persistence.entity.DisciplinaProfesorEntity;
import com.utm.msei.persistence.entity.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DisciplinaProfesorRepository extends JpaRepository<DisciplinaProfesorEntity, Integer> {
    @Transactional
    @Modifying
    @Query("delete from DisciplinaProfesorEntity d where d.idDisciplina = ?1")
    int deleteByIdDisciplina(DisciplinaEntity idDisciplina);
    long deleteByIdProfesorAndIdDisciplina(ProfesorEntity idProfesor, DisciplinaEntity idDisciplina);
    List<DisciplinaProfesorEntity> findAllDiscByIdProfesor(ProfesorEntity idProfesor);
    @Transactional
    @Modifying
    @Query("update DisciplinaProfesorEntity d set d.idDisciplina = ?1 where d.idProfesor = ?2")
    int updateIdDisciplinaByIdProfesor(DisciplinaEntity idDisciplina, ProfesorEntity idProfesor);
}
