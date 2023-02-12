package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.ParintiDto;
import com.utm.msei.persistence.entity.ParintiEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ParintiMapper extends Mapper<ParintiDto, ParintiEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ParintiEntity partialUpdate(ParintiDto parintiDto, @MappingTarget ParintiEntity parintiEntity);
}
