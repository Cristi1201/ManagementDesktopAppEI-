package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.TemaAcasaDto;
import com.utm.msei.persistence.dto.UserDto;
import com.utm.msei.persistence.mapper.TemaAcasaMapper;
import com.utm.msei.persistence.mapper.UserMapper;
import com.utm.msei.persistence.repository.TemaAcasaRepository;
import com.utm.msei.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamAcasaService {

    private final TemaAcasaRepository temaAcasaRepository;

    private final TemaAcasaMapper temaAcasaMapper;

    @Autowired
    public TeamAcasaService(TemaAcasaRepository temaAcasaRepository, TemaAcasaMapper temaAcasaMapper) {
        this.temaAcasaRepository = temaAcasaRepository;
        this.temaAcasaMapper = temaAcasaMapper;
    }

    @Transactional
    public TemaAcasaDto save(TemaAcasaDto temaAcasaDto) {
        return temaAcasaMapper.toDto(temaAcasaRepository.save(temaAcasaMapper.toEntity(temaAcasaDto)));
    }
}
