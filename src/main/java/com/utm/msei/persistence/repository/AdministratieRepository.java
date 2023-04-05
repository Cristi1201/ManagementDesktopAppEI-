package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.AdministratieEntity;
import com.utm.msei.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdministratieRepository extends JpaRepository<AdministratieEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update AdministratieEntity a set a.idUser = ?1")
    int updateIdUserBy(UserEntity idUser);

    AdministratieEntity findByIdUser_Id(int id);
}
