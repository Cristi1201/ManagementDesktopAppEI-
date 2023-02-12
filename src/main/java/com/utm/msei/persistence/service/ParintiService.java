package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.ParintiDto;
import com.utm.msei.persistence.dto.ProfesorDto;
import com.utm.msei.persistence.mapper.ParintiMapper;
import com.utm.msei.persistence.mapper.ProfesorMapper;
import com.utm.msei.persistence.repository.ParintiRepository;
import com.utm.msei.persistence.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParintiService {

    private final ParintiRepository parintiRepository;

    private final ParintiMapper parintiMapper;

    @Autowired
    public ParintiService(ParintiRepository parintiRepository, ParintiMapper parintiMapper) {
        this.parintiRepository = parintiRepository;
        this.parintiMapper = parintiMapper;
    }

    @Transactional
    public ParintiDto save(ParintiDto parintiDto) {
        return parintiMapper.toDto(parintiRepository.save(parintiMapper.toEntity(parintiDto)));
    }
}
