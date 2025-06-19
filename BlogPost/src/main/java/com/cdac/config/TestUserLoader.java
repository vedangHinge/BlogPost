package com.cdac.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cdac.entity.User;
import com.cdac.repo.UserRepo;

import jakarta.annotation.PostConstruct;

@Component
public class TestUserLoader {
	@Autowired
private UserRepo userRepo;
	@Autowired
private PasswordEncoder passwordEncoder;
@PostConstruct
public void loadTestUser() {
	String email="user@test.com";
	userRepo.findByEmail(email).orElseGet(()->{
		User user=User.builder().name("Test User").email(email)
				.password(passwordEncoder.encode("password"))
				
				.build();
		return userRepo.save(user);
	});
}
}
