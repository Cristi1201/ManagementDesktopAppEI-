package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update UserEntity u set u.nume = ?1, u.prenume = ?2, u.userType = ?3, u.idnp = ?4, u.telefon = ?5, u.dataNastere = ?6 WHERE u.id = ?7")
    int updateNumeAndPrenumeAndUserTypeAndIdnpAndTelefonAndDataNastereBy(String nume, String prenume, String userType, String idnp, String telefon, LocalDate dataNastere, int id);
    @Transactional
    @Modifying
    @Query("update UserEntity u set u.poza = ?1")
    int updatePozaBy(byte[] poza);
    UserEntity findByEmail(String email);

    Optional<UserEntity> findOneById(int id);

    @Transactional
    @Modifying
    @Query("update UserEntity u set u.nume = ?1, u.prenume = ?2")
    int update(String nume, String prenume);
}
