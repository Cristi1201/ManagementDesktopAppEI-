package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.OrarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrarRepository extends JpaRepository<OrarEntity, Integer> {
}
