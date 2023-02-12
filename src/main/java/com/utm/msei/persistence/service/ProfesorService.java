package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.ProfesorDto;
import com.utm.msei.persistence.dto.TemaAcasaDto;
import com.utm.msei.persistence.mapper.ProfesorMapper;
import com.utm.msei.persistence.mapper.TemaAcasaMapper;
import com.utm.msei.persistence.repository.ProfesorRepository;
import com.utm.msei.persistence.repository.TemaAcasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    private final ProfesorMapper profesorMapper;

    @Autowired
    public ProfesorService(ProfesorRepository profesorRepository, ProfesorMapper profesorMapper) {
        this.profesorRepository = profesorRepository;
        this.profesorMapper = profesorMapper;
    }

    @Transactional
    public ProfesorDto save(ProfesorDto profesorDto) {
        return profesorMapper.toDto(profesorRepository.save(profesorMapper.toEntity(profesorDto)));
    }
}
