package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.ActivitateDto;
import com.utm.msei.persistence.entity.ActivitateEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ActivitateMapper extends Mapper<ActivitateDto, ActivitateEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ActivitateEntity partialUpdate(ActivitateDto activitateDto, @MappingTarget ActivitateEntity activitateEntity);
}
