package com.cdac.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface AuthenticationService {
UserDetails authenticate(String email,String password);
String generateToken(UserDetails userDetails);
String getUserNameFromJwtToken(Claims claims);
Claims validateJwtToken(String token);
public Authentication populateAuthenticationTokenFromJWT(String jwt);
}
