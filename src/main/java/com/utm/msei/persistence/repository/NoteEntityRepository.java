package com.utm.msei.persistence.repository;

import com.utm.msei.persistence.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteEntityRepository extends JpaRepository<NoteEntity, Integer> {
}
