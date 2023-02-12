package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.AbsenteDto;
import com.utm.msei.persistence.dto.ActivitateDto;
import com.utm.msei.persistence.mapper.AbsenteMapper;
import com.utm.msei.persistence.mapper.ActivitateMapper;
import com.utm.msei.persistence.repository.AbsenteRepository;
import com.utm.msei.persistence.repository.ActivitateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AbsenteService {

    private final AbsenteRepository absenteRepository;

    private final AbsenteMapper absenteMapper;

    @Autowired
    public AbsenteService(AbsenteRepository absenteRepository, AbsenteMapper absenteMapper) {
        this.absenteRepository = absenteRepository;
        this.absenteMapper = absenteMapper;
    }

    @Transactional
    public AbsenteDto save(AbsenteDto absenteDto) {
        return absenteMapper.toDto(absenteRepository.save(absenteMapper.toEntity(absenteDto)));
    }
}
