package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.MamaEntity;
import com.utm.msei.persistence.entity.TataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MamaRepository extends JpaRepository<MamaEntity, Integer> {
}
