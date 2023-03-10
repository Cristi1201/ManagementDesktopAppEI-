package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.DurataLectieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DurataLectieRepository extends JpaRepository<DurataLectieEntity, Integer> {
}
