package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorEntityRepository extends JpaRepository<ProfesorEntity, Integer> {
}
