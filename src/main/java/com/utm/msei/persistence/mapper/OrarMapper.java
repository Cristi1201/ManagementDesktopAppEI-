package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.OrarDto;
import com.utm.msei.persistence.entity.OrarEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrarMapper extends Mapper<OrarDto, OrarEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrarEntity partialUpdate(OrarDto orarDto, @MappingTarget OrarEntity orarEntity);
}
