package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.DisciplinaDto;
import com.utm.msei.persistence.dto.DisciplinaProfesorDto;
import com.utm.msei.persistence.mapper.DisciplinaMapper;
import com.utm.msei.persistence.mapper.DisciplinaProfesorMapper;
import com.utm.msei.persistence.repository.DisciplinaProfesorRepository;
import com.utm.msei.persistence.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    private final DisciplinaMapper disciplinaMapper;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository, DisciplinaMapper disciplinaMapper) {
        this.disciplinaRepository = disciplinaRepository;
        this.disciplinaMapper = disciplinaMapper;
    }

    @Transactional
    public DisciplinaDto save(DisciplinaDto disciplinaDto) {
        return disciplinaMapper.toDto(disciplinaRepository.save(disciplinaMapper.toEntity(disciplinaDto)));
    }
}
