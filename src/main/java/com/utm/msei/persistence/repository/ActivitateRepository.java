package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.ActivitateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivitateRepository extends JpaRepository<ActivitateEntity, Integer> {


}
