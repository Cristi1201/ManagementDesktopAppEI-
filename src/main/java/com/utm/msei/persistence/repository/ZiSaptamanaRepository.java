package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.ZiSaptamanaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ZiSaptamanaRepository extends JpaRepository<ZiSaptamanaEntity, Integer> {

//    Optional<ZiSaptamanaEntity> findOneById(Integer id);

}
