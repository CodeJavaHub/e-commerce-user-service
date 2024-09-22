package com.ecom.userservice.service;

import com.ecom.userservice.dto.LoginRequestDto;
import com.ecom.userservice.dto.LoginResponseDto;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.entity.User;
import java.util.List;

public interface UserService {

  LoginResponseDto saveUser(UserDto userDto);

  List<UserDto> getAllUsers();

  User authenticate(LoginRequestDto loginRequest);

  User getUserById(Long id);

  boolean updateUser(int i, String johnDoe);
}
