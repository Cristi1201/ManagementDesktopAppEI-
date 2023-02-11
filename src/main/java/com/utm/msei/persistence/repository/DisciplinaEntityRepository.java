package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaEntityRepository extends JpaRepository<DisciplinaEntity, Integer> {
}
