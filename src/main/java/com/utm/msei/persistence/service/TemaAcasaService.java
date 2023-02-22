package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.TemaAcasaDto;
import com.utm.msei.persistence.mapper.TemaAcasaMapper;
import com.utm.msei.persistence.repository.TemaAcasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TemaAcasaService {

    private final TemaAcasaRepository temaAcasaRepository;

    private final TemaAcasaMapper temaAcasaMapper;

    @Autowired
    public TemaAcasaService(TemaAcasaRepository temaAcasaRepository, TemaAcasaMapper temaAcasaMapper) {
        this.temaAcasaRepository = temaAcasaRepository;
        this.temaAcasaMapper = temaAcasaMapper;
    }

    @Transactional
    public TemaAcasaDto save(TemaAcasaDto temaAcasaDto) {
        return temaAcasaMapper.toDto(temaAcasaRepository.save(temaAcasaMapper.toEntity(temaAcasaDto)));
    }
}
