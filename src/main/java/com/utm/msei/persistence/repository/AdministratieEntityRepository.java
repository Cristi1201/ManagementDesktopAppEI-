package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.AdministratieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdministratieEntityRepository extends JpaRepository<AdministratieEntity, Integer> {
}
