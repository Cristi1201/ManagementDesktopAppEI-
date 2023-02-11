package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.ElevEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElevEntityRepository extends JpaRepository<ElevEntity, Integer> {
}
