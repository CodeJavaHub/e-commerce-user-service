package com.ecom.userservice.service.impl;

import com.ecom.userservice.common.exceptions.DuplicateUserFoundException;
import com.ecom.userservice.controller.UserController;
import com.ecom.userservice.dto.LoginResponse;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.entity.User;
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

  // TODO Need to use map struct for entity Mapping
  @Override
  public LoginResponse saveUser(UserDto userDto) {
    Optional<User> existingUserByEmail = userRepository.findByEmail(userDto.getEmail());
    Optional<User> existingUserByPhone = userRepository.findByPhone(userDto.getPhone());

    if (existingUserByEmail.isPresent()) {
      throw new DuplicateUserFoundException("Email is already in use.");
    }

    if (existingUserByPhone.isPresent()) {
      throw new DuplicateUserFoundException("Phone number is already in use.");
    }

    User user = new User();
    user.setEmail(userDto.getEmail());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setGender(userDto.getGender());
    user.setRole(userDto.getRole());
    user.setPhone(userDto.getPhone());
    user.setPassword(userDto.getPassword());
    userRepository.save(user);
    return new LoginResponse("Success", "User saved successfully.");
  }

  @Override
  public List<UserDto> getAllUsers() {
    List<User> userList = userRepository.findAll();
    return userList.stream()
        .map(this::convertToDto)
        .collect(Collectors.toList());
  }

  // Method to convert User entity to UserDto
  private UserDto convertToDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setEmail(user.getEmail());
    userDto.setFirstName(user.getFirstName());
    userDto.setLastName(user.getLastName());
    userDto.setGender(user.getGender());
    userDto.setRole(user.getRole());
    userDto.setPhone(user.getPhone());
    userDto.setPassword(user.getPassword()); // Omit if not needed

    return userDto;
  }
}
