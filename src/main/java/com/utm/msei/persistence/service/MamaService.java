package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.MamaDto;
import com.utm.msei.persistence.mapper.MamaMapper;
import com.utm.msei.persistence.repository.MamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MamaService {

    private final MamaRepository mamaRepository;

    private final MamaMapper mamaMapper;

    @Autowired
    public MamaService(MamaRepository mamaRepository, MamaMapper mamaMapper) {
        this.mamaRepository = mamaRepository;
        this.mamaMapper = mamaMapper;
    }

    @Transactional
    public MamaDto save(MamaDto mamaDto) {
        return mamaMapper.toDto(mamaRepository.save(mamaMapper.toEntity(mamaDto)));
    }

    @Transactional
    public List<MamaDto> getAll() {
        return mamaRepository.findAll().stream().map(mamaMapper::toDto).collect(Collectors.toList());
    }
}
