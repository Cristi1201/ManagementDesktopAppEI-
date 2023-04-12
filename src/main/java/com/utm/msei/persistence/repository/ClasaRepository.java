package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.ClasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasaRepository extends JpaRepository<ClasaEntity, Integer> {
    ClasaEntity findByNume(String clasaName);
}
