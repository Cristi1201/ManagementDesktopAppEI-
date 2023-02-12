package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.DisciplinaProfesorDto;
import com.utm.msei.persistence.dto.DurataLectieDto;
import com.utm.msei.persistence.mapper.DisciplinaProfesorMapper;
import com.utm.msei.persistence.mapper.DurataLectieMapper;
import com.utm.msei.persistence.repository.DisciplinaProfesorRepository;
import com.utm.msei.persistence.repository.DurataLectieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisciplinaProfesorService {

    private final DisciplinaProfesorRepository disciplinaProfesorRepository;

    private final DisciplinaProfesorMapper disciplinaProfesorMapper;

    @Autowired
    public DisciplinaProfesorService(DisciplinaProfesorRepository disciplinaProfesorRepository, DisciplinaProfesorMapper disciplinaProfesorMapper) {
        this.disciplinaProfesorRepository = disciplinaProfesorRepository;
        this.disciplinaProfesorMapper = disciplinaProfesorMapper;
    }

    @Transactional
    public DisciplinaProfesorDto save(DisciplinaProfesorDto disciplinaProfesorDto) {
        return disciplinaProfesorMapper.toDto(disciplinaProfesorRepository.save(disciplinaProfesorMapper.toEntity(disciplinaProfesorDto)));
    }
}
