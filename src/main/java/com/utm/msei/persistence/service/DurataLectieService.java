package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.DurataLectieDto;
import com.utm.msei.persistence.mapper.DurataLectieMapper;
import com.utm.msei.persistence.repository.DurataLectieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<DurataLectieDto> getAll() {
        return durataLectieRepository.findAll().stream().map(durataLectieMapper::toDto).collect(Collectors.toList());
    }
}
