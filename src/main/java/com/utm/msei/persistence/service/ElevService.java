package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.ElevDto;
import com.utm.msei.persistence.dto.MesajeDto;
import com.utm.msei.persistence.mapper.ElevMapper;
import com.utm.msei.persistence.mapper.MesajeMapper;
import com.utm.msei.persistence.repository.ElevRepository;
import com.utm.msei.persistence.repository.MesajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ElevService {

    private final ElevRepository elevRepository;

    private final ElevMapper elevMapper;

    @Autowired
    public ElevService(ElevRepository elevRepository, ElevMapper elevMapper) {
        this.elevRepository = elevRepository;
        this.elevMapper = elevMapper;
    }

    @Transactional
    public ElevDto save(ElevDto elevDto) {
        return elevMapper.toDto(elevRepository.save(elevMapper.toEntity(elevDto)));
    }
}
