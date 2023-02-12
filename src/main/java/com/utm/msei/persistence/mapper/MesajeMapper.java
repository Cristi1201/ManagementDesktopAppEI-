package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.MesajeDto;
import com.utm.msei.persistence.entity.MesajeEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MesajeMapper extends Mapper<MesajeDto, MesajeEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MesajeEntity partialUpdate(MesajeDto mesajeDto, @MappingTarget MesajeEntity mesajeEntity);
}
