package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.UserDto;
import com.utm.msei.persistence.entity.UserEntity;
import org.mapstruct.*;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper extends Mapper<UserDto, UserEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserDto userDto, @MappingTarget UserEntity userEntity);
}
