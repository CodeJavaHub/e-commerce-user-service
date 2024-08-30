package com.ecom.e_commerce_user_service.controller;

import com.ecom.e_commerce_user_service.dto.LoginRequest;
import com.ecom.e_commerce_user_service.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginRequest){
        return null;
    }
}
