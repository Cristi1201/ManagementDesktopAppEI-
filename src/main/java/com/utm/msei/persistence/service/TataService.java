package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.TataDto;
import com.utm.msei.persistence.mapper.TataMapper;
import com.utm.msei.persistence.repository.TataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TataService {

    private final TataRepository tataRepository;

    private final TataMapper tataMapper;

    @Autowired
    public TataService(TataRepository tataRepository, TataMapper tataMapper) {
        this.tataRepository = tataRepository;
        this.tataMapper = tataMapper;
    }

    @Transactional
    public TataDto save(TataDto tataDto) {
        return tataMapper.toDto(tataRepository.save(tataMapper.toEntity(tataDto)));
    }

    @Transactional
    public List<TataDto> getAll() {
        return tataRepository.findAll().stream().map(tataMapper::toDto).collect(Collectors.toList());
    }
}
