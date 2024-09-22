package com.ecom.userservice.controller;

import com.ecom.userservice.dto.UserDto;
import com.ecom.userservice.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {


  @Autowired private UserService userService;
  @GetMapping("/all")
  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<UserDto> userList = userService.getAllUsers();
    return ResponseEntity.ok(userList);
  }
}
