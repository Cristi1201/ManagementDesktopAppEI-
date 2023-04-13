package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.TataDto;
import com.utm.msei.persistence.entity.TataEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TataMapper extends Mapper<TataDto, TataEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TataEntity partialUpdate(TataDto tataDto, @MappingTarget TataEntity tataEntity);
}
