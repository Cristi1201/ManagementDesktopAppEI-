package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.ZiSaptamanaEntityDto;
import com.utm.msei.persistence.entity.ZiSaptamanaEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ZiSaptamanaEntityMapper {


    ZiSaptamanaEntityDto toDto(ZiSaptamanaEntity ziSaptamanaEntity);


    ZiSaptamanaEntity toEntity(ZiSaptamanaEntityDto absenteEntityDto);
}
