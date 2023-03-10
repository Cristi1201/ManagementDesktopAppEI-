package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);

    Optional<UserEntity> findOneById(int id);
}
