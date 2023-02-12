package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.ClasaDto;
import com.utm.msei.persistence.dto.DisciplinaProfesorDto;
import com.utm.msei.persistence.mapper.ClasaMapper;
import com.utm.msei.persistence.mapper.DisciplinaProfesorMapper;
import com.utm.msei.persistence.repository.ClasaRepository;
import com.utm.msei.persistence.repository.DisciplinaProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClasaService {

    private final ClasaRepository clasaRepository;

    private final ClasaMapper clasaMapper;

    @Autowired
    public ClasaService(ClasaRepository clasaRepository, ClasaMapper clasaMapper) {
        this.clasaRepository = clasaRepository;
        this.clasaMapper = clasaMapper;
    }

    @Transactional
    public ClasaDto save(ClasaDto clasaDto) {
        return clasaMapper.toDto(clasaRepository.save(clasaMapper.toEntity(clasaDto)));
    }
}
