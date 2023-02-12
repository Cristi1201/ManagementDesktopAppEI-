package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.NoteDto;
import com.utm.msei.persistence.entity.NoteEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface NoteMapper extends Mapper<NoteDto, NoteEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    NoteEntity partialUpdate(NoteDto noteDto, @MappingTarget NoteEntity noteEntity);
}
