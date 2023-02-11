package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.ZiSaptamanaEntityDto;
import com.utm.msei.persistence.mapper.ZiSaptamanaEntityMapperImpl;
import com.utm.msei.persistence.repository.ZiSaptamanaEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ZiSaptamanaService {

    private final ZiSaptamanaEntityRepository ZiSaptamanaEntityRepository;


    @Autowired
    public ZiSaptamanaService(ZiSaptamanaEntityRepository ziSaptamanaEntityRepository) {
        this.ZiSaptamanaEntityRepository = ziSaptamanaEntityRepository;
    }

    @Transactional
    public ZiSaptamanaEntityDto save(ZiSaptamanaEntityDto ziSaptamanaEntityDto) {
        ZiSaptamanaEntityMapperImpl ziSaptamanaEntityMapper = new ZiSaptamanaEntityMapperImpl();
        return ziSaptamanaEntityMapper.toDto(ZiSaptamanaEntityRepository.save(ziSaptamanaEntityMapper.toEntity(ziSaptamanaEntityDto)));
    }

}
