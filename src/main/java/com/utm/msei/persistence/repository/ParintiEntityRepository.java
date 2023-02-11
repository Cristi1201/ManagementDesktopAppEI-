package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.ParintiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParintiEntityRepository extends JpaRepository<ParintiEntity, Integer> {
}
