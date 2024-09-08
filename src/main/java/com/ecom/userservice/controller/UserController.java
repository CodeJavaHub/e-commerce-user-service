package com.ecom.userservice.controller;

import com.ecom.userservice.dto.LoginRequestDto;
import com.ecom.userservice.dto.LoginResponseDto;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.entity.User;
import com.ecom.userservice.service.UserService;
import com.ecom.userservice.service.impl.JwtServiceImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth/user")
public class UserController {

  @Autowired private UserService userService;

 @Autowired private JwtServiceImpl jwtService;


  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@RequestBody LoginRequestDto loginRequest) {
    User authenticatedUser = userService.authenticate(loginRequest);
    String jwtToken = jwtService.generateToken(authenticatedUser);
    LoginResponseDto loginResponse = new LoginResponseDto("Login Success!", jwtToken,jwtService.getExpirationTime(),null);
    return ResponseEntity.ok(loginResponse);
  }

  @PostMapping("/signup")
  public ResponseEntity<LoginResponseDto> signUp(
      @RequestHeader(value = "trace_id", required = false) String traceId,
      @RequestBody UserDto userDto) {
    LoginResponseDto loginResponse = userService.saveUser(userDto);
    log.info("{}: User Request: {}", traceId, userDto.toString());
    return ResponseEntity.ok(loginResponse);
  }

  @GetMapping("/all")
  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<UserDto> userList = userService.getAllUsers();
    return ResponseEntity.ok(userList);
  }
}
