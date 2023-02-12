package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.ClasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasaRepository extends JpaRepository<ClasaEntity, Integer> {
}
