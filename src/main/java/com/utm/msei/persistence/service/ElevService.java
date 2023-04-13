package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.*;
import com.utm.msei.persistence.mapper.ElevMapper;
import com.utm.msei.persistence.mapper.MamaMapper;
import com.utm.msei.persistence.mapper.MesajeMapper;
import com.utm.msei.persistence.mapper.TataMapper;
import com.utm.msei.persistence.repository.ElevRepository;
import com.utm.msei.persistence.repository.MesajeRepository;
import com.utm.msei.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElevService {

    private final ElevRepository elevRepository;

    private final ElevMapper elevMapper;
    private final UserRepository userRepository;
    private final TataMapper tataMapper;
    private final MamaMapper mamaMapper;

    @Autowired
    public ElevService(ElevRepository elevRepository, ElevMapper elevMapper,
                       UserRepository userRepository, TataMapper tataMapper, MamaMapper mamaMapper) {
        this.elevRepository = elevRepository;
        this.elevMapper = elevMapper;
        this.userRepository = userRepository;
        this.tataMapper = tataMapper;
        this.mamaMapper = mamaMapper;
    }

    @Transactional
    public ElevDto save(ElevDto elevDto) {
        return elevMapper.toDto(elevRepository.save(elevMapper.toEntity(elevDto)));
    }

    public List<ElevDto> getElevi(ClasaDto clasa) {
        return elevRepository.findByIdClasaOrderByIdUser_NumeAscIdUser_PrenumeAsc(clasa.getId()).stream().map(elevMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public int updateTata(int id, TataDto tata) {
        return elevRepository.updateIdTataById(tataMapper.toEntity(tata), id);
    }
    @Transactional
    public int updateMama(int id, MamaDto mama) {
        return elevRepository.updateIdMamaById(mamaMapper.toEntity(mama), id);
    }

//    public int updateUser(ElevDto newElev) {
//        return userRepository.updateNumeAndPrenumeAndIdnpAndTelefonAndDataNastereById(newElev.getIdUser().getNume(), newElev.getIdUser().getPrenume(), newElev.getIdUser().getIdnp(), newElev.getIdUser().getTelefon(), newElev.getIdUser().getDataNastere(), newElev.getIdUser().getId());
//    }
}
