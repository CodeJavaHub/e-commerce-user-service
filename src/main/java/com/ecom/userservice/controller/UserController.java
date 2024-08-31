package com.ecom.userservice.controller;

import com.ecom.userservice.dto.LoginRequest;
import com.ecom.userservice.dto.LoginResponse;
import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

  @Autowired private UserService userService;

  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @PostMapping("/login")
  public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
    return null;
  }

  // TODO Need to add Enum into the DTO and Add enum validation for the request
  @PostMapping("/signup")
  public ResponseEntity<LoginResponse> signUp(
      @RequestHeader(value = "trace_id", required = false) String traceId,
      @Valid @RequestBody UserDto userDto) {
    System.out.println("User Request" + userDto.toString());
    LoginResponse loginResponse = userService.saveUser(userDto);
    log.info(traceId + ": User Request: {}", userDto.toString());
    return new ResponseEntity(loginResponse, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<UserDto>> getAllUsers(){
    List<UserDto> userList = userService.getAllUsers();
    return new ResponseEntity<>(userList, HttpStatus.OK);
  }
}
