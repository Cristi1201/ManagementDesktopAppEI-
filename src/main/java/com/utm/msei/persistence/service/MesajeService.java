package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.MesajeDto;
import com.utm.msei.persistence.dto.NoteDto;
import com.utm.msei.persistence.mapper.MesajeMapper;
import com.utm.msei.persistence.mapper.NoteMapper;
import com.utm.msei.persistence.repository.MesajeRepository;
import com.utm.msei.persistence.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MesajeService {

    private final MesajeRepository mesajeRepository;

    private final MesajeMapper mesajeMapper;

    @Autowired
    public MesajeService(MesajeRepository mesajeRepository, MesajeMapper mesajeMapper) {
        this.mesajeRepository = mesajeRepository;
        this.mesajeMapper = mesajeMapper;
    }

    @Transactional
    public MesajeDto save(MesajeDto mesajeDto) {
        return mesajeMapper.toDto(mesajeRepository.save(mesajeMapper.toEntity(mesajeDto)));
    }
}
