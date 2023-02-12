package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.TemaAcasaDto;
import com.utm.msei.persistence.entity.TemaAcasaEntity;
import com.utm.msei.persistence.mapper.Mapper;
import org.mapstruct.*;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TemaAcasaMapper extends Mapper<TemaAcasaDto, TemaAcasaEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TemaAcasaEntity partialUpdate(TemaAcasaDto temaAcasaDto, @MappingTarget TemaAcasaEntity temaAcasaEntity);
}
