package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.AdministratieDto;
import com.utm.msei.persistence.entity.AdministratieEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AdministratieMapper extends Mapper<AdministratieDto, AdministratieEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdministratieEntity partialUpdate(AdministratieDto administratieDto, @MappingTarget AdministratieEntity administratieEntity);
}
