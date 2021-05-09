package com.brightcoding.app.ws.services;

import java.util.List;

import com.brightcoding.app.ws.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.brightcoding.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService  {
	
	UserDto createUser(UserDto user);
    
	UserDto getUser(String email);
	
	UserDto getUserByUserId(String userId);
	
	UserDto updateUser(String id, UserDto userDto);
	public void save(UserEntity user);

	void deleteUser(String userId);
	
	List<UserDto> getUsers(int page, int limit, String search, int status);

}
