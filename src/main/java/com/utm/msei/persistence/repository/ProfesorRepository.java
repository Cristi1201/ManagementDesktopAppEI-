package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.dto.ProfesorDto;
import com.utm.msei.persistence.dto.UserDto;
import com.utm.msei.persistence.entity.ProfesorEntity;
import com.utm.msei.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProfesorRepository extends JpaRepository<ProfesorEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update ProfesorEntity p set p.idUser = ?1")
    int updateIdUserBy(UserEntity idUser);

    ProfesorEntity findByIdUser_Id(int id);
}
