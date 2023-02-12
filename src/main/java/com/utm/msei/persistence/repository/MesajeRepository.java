package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.MesajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesajeRepository extends JpaRepository<MesajeEntity, Integer> {
}
