package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.ClasaDto;
import com.utm.msei.persistence.mapper.ClasaMapper;
import com.utm.msei.persistence.repository.ClasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<ClasaDto> getAll() {
        return clasaRepository.findAll().stream().map(c -> clasaMapper.toDto(c)).collect(Collectors.toList());
    }

    public ClasaDto getClassDto(String clasaName) {
        return clasaMapper.toDto(clasaRepository.findByNume(clasaName));
    }
}
