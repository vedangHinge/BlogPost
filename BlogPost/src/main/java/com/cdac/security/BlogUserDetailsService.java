package com.cdac.security;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cdac.entity.User;
import com.cdac.repo.UserRepo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Service

public class BlogUserDetailsService implements UserDetailsService{
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user=userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Name not found"+email));
		return new BlogUserDetails(user);
	}

}
