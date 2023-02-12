package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.DurataLectieDto;
import com.utm.msei.persistence.dto.ElevDto;
import com.utm.msei.persistence.mapper.DurataLectieMapper;
import com.utm.msei.persistence.mapper.ElevMapper;
import com.utm.msei.persistence.repository.DurataLectieRepository;
import com.utm.msei.persistence.repository.ElevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DurataLectieService {

    private final DurataLectieRepository durataLectieRepository;

    private final DurataLectieMapper durataLectieMapper;

    @Autowired
    public DurataLectieService(DurataLectieRepository durataLectieRepository, DurataLectieMapper durataLectieMapper) {
        this.durataLectieRepository = durataLectieRepository;
        this.durataLectieMapper = durataLectieMapper;
    }

    @Transactional
    public DurataLectieDto save(DurataLectieDto durataLectieDto) {
        return durataLectieMapper.toDto(durataLectieRepository.save(durataLectieMapper.toEntity(durataLectieDto)));
    }
}
