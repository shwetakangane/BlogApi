package com.start.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.start.blog.entity.User;
import com.start.blog.exception.ResourceNotFoundException;
import com.start.blog.payload.UserDto;
import com.start.blog.repository.UserRepository;
import com.start.blog.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	UserRepository userRepository;
	ModelMapper modelMapper;
	

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		
		return userToDto(userRepository.save(dtoToUser(userDto)));
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		User user = userRepository.findById(id).orElseThrow((() -> new ResourceNotFoundException("User","id",id)));
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		User save = userRepository.save(user);
		return userToDto(save);
	}

	@Override
	public List<UserDto> getallUsers() {
		List<User> users = userRepository.findAll();
		List<UserDto> userDtos = users.stream().map(user->userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = userRepository.findById(id).orElseThrow((() -> new ResourceNotFoundException("User","id",id)));
		return userToDto(user);
	}

	@Override
	public void deleteUser(Integer id) {
		User user = userRepository.findById(id).orElseThrow((() -> new ResourceNotFoundException("User","id",id)));
		userRepository.delete(user);
		
	}
	
	private User dtoToUser(UserDto userDto) {
		//User user=new User(userDto.getId(),userDto.getName(),userDto.getEmail(),userDto.getPassword(),userDto.getAbout());
		User user = modelMapper.map(userDto, User.class);
		return user;	
	}
	
	private UserDto userToDto(User user) {
		//UserDto userDto=new UserDto(user.getId(),user.getName(),user.getEmail(),user.getPassword(),user.getAbout());
		UserDto userDto = modelMapper.map(user, UserDto.class);

		return userDto;	
	}

}
