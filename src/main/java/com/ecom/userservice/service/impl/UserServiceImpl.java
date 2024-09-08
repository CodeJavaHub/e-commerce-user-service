package com.ecom.userservice.service.impl;

import com.ecom.userservice.common.exceptions.DuplicateUserFoundException;
import com.ecom.userservice.dto.LoginRequestDto;
import com.ecom.userservice.dto.LoginResponseDto;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.entity.User;
import com.ecom.userservice.mapper.UserMapper;
import com.ecom.userservice.repository.UserRepository;
import com.ecom.userservice.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private AuthenticationManager authenticationManager;

  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

  @Override
  public LoginResponseDto saveUser(UserDto userDto) {
    if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
      throw new DuplicateUserFoundException("Email is already in use.");
    }
    // Proceed with saving the new user if no duplicate is found
    User newUser = UserMapper.USER_MAPPER.convertToEntity(userDto);
    newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
    newUser.setIsLocked(false);
    newUser.setIsEnable(true);
    userRepository.save(newUser);

    return new LoginResponseDto();
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<User> userList = userRepository.findAll();
    return userList.stream().map(UserMapper.USER_MAPPER::convertToDto).toList();
  }

  @Override
  public User authenticate(LoginRequestDto loginRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(), loginRequest.getPassword()));
    return userRepository.findByEmail(loginRequest.getUsername()).orElseThrow();
  }
}
