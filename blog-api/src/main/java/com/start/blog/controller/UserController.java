package com.start.blog.controller;

import java.util.List;

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

import com.start.blog.payload.ApiResponse;
import com.start.blog.payload.UserDto;
import com.start.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	UserService userService;
	
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.CREATED);	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable int id){
		UserDto updateUser = userService.updateUser(userDto,id);
		return ResponseEntity.ok(updateUser);	
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> getallUsers = userService.getallUsers();
		return ResponseEntity.ok(getallUsers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int id){
		UserDto user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id){
		userService.deleteUser(id);
		//return ResponseEntity.ok(Map.of("message","User is deleted successsfully!"));	
		return new ResponseEntity<>(new ApiResponse("User is deleted successsfully!",true),HttpStatus.OK);
	}
	

}
