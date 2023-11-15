package com.ssafy.api.user.model.service;

import org.springframework.stereotype.Service;

public interface UserService {
	
	public String[] jwtlogin(String name, String id);

}
