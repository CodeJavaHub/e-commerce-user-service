package com.ecom.userservice.service;

import com.ecom.userservice.dto.LoginResponse;
import com.ecom.userservice.dto.UserDto;
import java.util.List;

public interface UserService {

  LoginResponse saveUser(UserDto userDto);

  List<UserDto> getAllUsers();
}
