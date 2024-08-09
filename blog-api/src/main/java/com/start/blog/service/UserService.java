package com.start.blog.service;

import java.util.List;

import com.start.blog.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(UserDto userDto,Integer id);
	List<UserDto> getallUsers();
	UserDto getUserById(Integer id);
	void deleteUser(Integer id);

}
