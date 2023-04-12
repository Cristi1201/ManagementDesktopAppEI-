package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.DisciplinaDto;
import com.utm.msei.persistence.mapper.DisciplinaMapper;
import com.utm.msei.persistence.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<DisciplinaDto> getAll() {
        return disciplinaMapper.toDto(disciplinaRepository.findAll());
    }

    public void update(DisciplinaDto disciplinaDto) {
        disciplinaRepository.updateDisciplinaById(disciplinaDto.getDisciplina(), disciplinaDto.getId());
    }

    public void delete(int id) {
        disciplinaRepository.deleteById(id);
    }

    public DisciplinaDto findByName(String disc) {
        return disciplinaMapper.toDto(disciplinaRepository.findByDisciplina(disc));
    }
}
