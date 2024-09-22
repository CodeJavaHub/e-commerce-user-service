package com.ecom.userservice.service.impl;

import com.ecom.userservice.common.exceptions.DuplicateUserFoundException;
import com.ecom.userservice.common.exceptions.UserNotFoundException;
import com.ecom.userservice.dto.LoginRequestDto;
import com.ecom.userservice.dto.LoginResponseDto;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.entity.User;
import com.ecom.userservice.mapper.UserMapper;
import com.ecom.userservice.repository.UserRepository;
import com.ecom.userservice.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

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

  @Override
  public User getUserById(Long id) {
    Optional<User> userOptional = userRepository.findById(id);
    User user = userOptional.orElse(null);
    return user;
  }

  @Override
  @Transactional
  public boolean updateUser(int id, String name) {
    // Retrieve the user using the ID and throw an exception if not found
    User existingUser =
        userRepository
            .findById((long) id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    existingUser.setFirstName(name);

    // Save the updated user
    userRepository.save(existingUser);

    return true;
  }
}
