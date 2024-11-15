package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDTO;
import com.blog.repo.UserRepo;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		
		User user = this.dtoToUser(userDTO);
		User savedUser=this.userRepo.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", " id ", userId));
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User updatedUser=this.userRepo.save(user);
		return this.userToDTO(updatedUser);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user"," id ", userId));
		return this.userToDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<User> users=this.userRepo.findAll();
		List<UserDTO> userDTOs=users.stream().map(user -> this.userToDTO(user)).collect(Collectors.toList());
		return userDTOs;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user"," id ", userId));
		this.userRepo.delete(user);
	}

	
	private User dtoToUser(UserDTO userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
		/*user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());*/
		
	    return user;
	}
	
	private UserDTO userToDTO(User user) {
		UserDTO userDTO= this.modelMapper.map(user, UserDTO.class);
		
		/*userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		userDTO.setAbout(user.getAbout());
		userDTO.setPassword(user.getPassword());*/
		return userDTO;
	}

	@Override
	public UserDTO loadUserByUsername(String username){
		//loading user from database by username
		//TODO create a different Exception as ResourceNotFoundException is taking 'long' as its last parameter 
		System.out.println("In CustomUserDetailService - loadUserByUsername method");
		User user=this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user", "email "+username, 0));
		System.out.println("user "+user);
		return userToDTO(user);
	}

}
