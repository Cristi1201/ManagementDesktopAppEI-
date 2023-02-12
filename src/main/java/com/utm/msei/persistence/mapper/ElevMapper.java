package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.ElevDto;
import com.utm.msei.persistence.entity.ElevEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ElevMapper extends Mapper<ElevDto, ElevEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ElevEntity partialUpdate(ElevDto elevDto, @MappingTarget ElevEntity elevEntity);
}
