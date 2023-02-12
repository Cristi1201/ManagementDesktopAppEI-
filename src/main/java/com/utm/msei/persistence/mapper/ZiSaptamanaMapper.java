package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.ZiSaptamanaDto;
import com.utm.msei.persistence.entity.ZiSaptamanaEntity;
import org.mapstruct.*;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ZiSaptamanaMapper extends Mapper<ZiSaptamanaDto, ZiSaptamanaEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ZiSaptamanaEntity partialUpdate(ZiSaptamanaDto ziSaptamanaDto, @MappingTarget ZiSaptamanaEntity ziSaptamanaEntity);
}
