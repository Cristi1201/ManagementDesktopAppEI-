package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.MamaDto;
import com.utm.msei.persistence.entity.MamaEntity;
import com.utm.msei.persistence.entity.TataEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MamaMapper extends Mapper<MamaDto, MamaEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TataEntity partialUpdate(MamaDto MamaDto, @MappingTarget TataEntity tataEntity);
}
