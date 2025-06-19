package com.cdac.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cdac.custom_exception.ResourceNotFoundException;
import com.cdac.entity.User;
import com.cdac.repo.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private UserRepo userRepo;
	@Override
	public User getById(UUID userId) {
		
		return userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("No user with such id"));
	}

}
