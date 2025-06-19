package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Dto.AuthResponse;
import com.cdac.Dto.LoginRequest;
import com.cdac.service.AuthenticationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
	
 private AuthenticationService authService;
 
 @PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
	 UserDetails userDetails= authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
	 AuthResponse authResponse= AuthResponse.builder().token( authService.generateToken(userDetails)).expiresIn(86400).build();
	 return ResponseEntity.ok(authResponse);
 }
}
