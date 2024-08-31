package com.ecom.userservice.service.impl;

import com.ecom.userservice.common.exceptions.DuplicateUserFoundException;
import com.ecom.userservice.controller.UserController;
import com.ecom.userservice.dto.LoginResponse;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.entity.User;
import com.ecom.userservice.mapper.UserMapper;
import com.ecom.userservice.repository.UserRepository;
import com.ecom.userservice.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

  @Override
  public LoginResponse saveUser(UserDto userDto) {
    User user =
        userRepository
            .findByEmail(userDto.getEmail())
            .orElseThrow(() -> new DuplicateUserFoundException("Email is already in use."));
    User newUser = UserMapper.USER_MAPPER.convertToEntity(userDto);
    userRepository.save(newUser);
    return new LoginResponse("Success", "User saved successfully.");
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<User> userList = userRepository.findAll();
    return userList.stream().map(UserMapper.USER_MAPPER::convertToDto).toList();
  }
}
