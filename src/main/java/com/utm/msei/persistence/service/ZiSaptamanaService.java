package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.ZiSaptamanaDto;
import com.utm.msei.persistence.mapper.ZiSaptamanaMapper;
import com.utm.msei.persistence.repository.ZiSaptamanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ZiSaptamanaService {

    private final ZiSaptamanaRepository ZiSaptamanaRepository;

    private final ZiSaptamanaMapper ziSaptamanaMapper;

    @Autowired
    public ZiSaptamanaService(ZiSaptamanaRepository ziSaptamanaRepository, ZiSaptamanaMapper ziSaptamanaMapper) {
        this.ZiSaptamanaRepository = ziSaptamanaRepository;
        this.ziSaptamanaMapper = ziSaptamanaMapper;
    }

    @Transactional
    public ZiSaptamanaDto save(ZiSaptamanaDto ziSaptamanaDto) {
        return ziSaptamanaMapper.toDto(ZiSaptamanaRepository.save(ziSaptamanaMapper.toEntity(ziSaptamanaDto)));
    }
}
