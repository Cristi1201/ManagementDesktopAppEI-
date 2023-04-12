package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Integer> {
    DisciplinaEntity findByDisciplina(String disciplina);
    @Transactional
    @Modifying
    @Query("update DisciplinaEntity d set d.disciplina = ?1 WHERE d.id = ?2")
    int updateDisciplinaById(String disciplina, int id);

    @Override
    void deleteById(Integer integer);
}
