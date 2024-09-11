package com.ecom.userservice.mapper;

import com.ecom.userservice.dto.UserDetailsDto;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDetailsMapper {

  UserDetailsMapper USER_DETAILS_MAPPER = Mappers.getMapper(UserDetailsMapper.class);

  User convertToEntity(UserDetailsDto userDetailsDto);

  UserDetailsDto convertToDto(User user);
}
