package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.OrarDto;
import com.utm.msei.persistence.dto.ParintiDto;
import com.utm.msei.persistence.mapper.OrarMapper;
import com.utm.msei.persistence.mapper.ParintiMapper;
import com.utm.msei.persistence.repository.OrarRepository;
import com.utm.msei.persistence.repository.ParintiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrarService {

    private final OrarRepository orarRepository;

    private final OrarMapper orarMapper;

    @Autowired
    public OrarService(OrarRepository orarRepository, OrarMapper orarMapper) {
        this.orarRepository = orarRepository;
        this.orarMapper = orarMapper;
    }

    @Transactional
    public OrarDto save(OrarDto orarDto) {
        return orarMapper.toDto(orarRepository.save(orarMapper.toEntity(orarDto)));
    }
}
