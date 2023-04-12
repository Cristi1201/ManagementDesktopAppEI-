package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.ProfesorDto;
import com.utm.msei.persistence.mapper.ProfesorMapper;
import com.utm.msei.persistence.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public void updateUser(ProfesorDto profesorDto) {
        profesorRepository.updateIdUserBy(profesorMapper.toEntity(profesorDto).getIdUser());
    }

    @Transactional
    public ProfesorDto findByUserId(int id) {
        return profesorMapper.toDto(profesorRepository.findByIdUser_Id(id));
    }

    @Transactional
    public List<ProfesorDto> getAll() {
        return profesorMapper.toDto(profesorRepository.findAll());
    }
}
