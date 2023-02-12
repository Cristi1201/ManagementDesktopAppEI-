package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.TemaAcasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaAcasaRepository extends JpaRepository<TemaAcasaEntity, Integer> {
}
