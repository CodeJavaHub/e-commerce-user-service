package com.ecom.userservice.mapper;

import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

  User convertToEntity(UserDto userDto);

  UserDto convertToDto(User user);
}
