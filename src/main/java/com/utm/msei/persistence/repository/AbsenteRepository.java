package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.AbsenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenteRepository extends JpaRepository<AbsenteEntity, Integer> {
}
