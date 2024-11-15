package com.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.repo.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user from database by username
		//TODO create a different Exception as ResourceNotFoundException is taking 'long' as its last parameter 
		System.out.println("In CustomUserDetailService - loadUserByUsername method");
		User user=this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user", "email "+username, 0));
		System.out.println("user "+user);
		return user;
	}

}
