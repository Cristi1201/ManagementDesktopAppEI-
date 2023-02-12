package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.ActivitateDto;
import com.utm.msei.persistence.mapper.ActivitateMapper;
import com.utm.msei.persistence.repository.ActivitateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivitateService {

    private final ActivitateRepository activitateRepository;

    private final ActivitateMapper activitateMapper;

    @Autowired
    public ActivitateService(ActivitateRepository activitateRepository, ActivitateMapper activitateMapper) {
        this.activitateRepository = activitateRepository;
        this.activitateMapper = activitateMapper;
    }

    @Transactional
    public ActivitateDto save(ActivitateDto activitateDto) {
        return activitateMapper.toDto(activitateRepository.save(activitateMapper.toEntity(activitateDto)));
    }
}
