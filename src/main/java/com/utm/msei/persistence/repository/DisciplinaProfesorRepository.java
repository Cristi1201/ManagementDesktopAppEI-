package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.DisciplinaProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaProfesorRepository extends JpaRepository<DisciplinaProfesorEntity, Integer> {
}
