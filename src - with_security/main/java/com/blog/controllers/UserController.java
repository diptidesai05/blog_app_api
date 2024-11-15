package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDTO;
import com.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//POST Create User
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		
		UserDTO createdUserDTO=this.userService.createUser(userDTO);
		return new ResponseEntity<>(createdUserDTO,HttpStatus.CREATED);
		
	}

	//PUT Update User
	@PutMapping("/{userID}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable("userID") Integer userID){
		
		UserDTO updatedUserDTO=this.userService.updateUser(userDTO, userID);
		return ResponseEntity.ok(updatedUserDTO);
	}
	
	//DELETE User
	//public ResponseEntity<?> deleteUser(@PathVariable("userID") Integer userID){
	@DeleteMapping("/{userID}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userID") Integer userID){		
		this.userService.deleteUser(userID);
		//return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
		//return new ResponseEntity(Map.of("message", "User deleted successfully"), HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true), HttpStatus.OK);
	}

    //GET all users
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getUsers(){
    	
    	return ResponseEntity.ok(this.userService.getAllUsers());
    }
    
    //GET user
    @GetMapping("/{userID}")
    public ResponseEntity<UserDTO> getSingleUsers(@PathVariable("userID") Integer userID){		
    	
    	return ResponseEntity.ok(this.userService.getUserById(userID));
    }
}
