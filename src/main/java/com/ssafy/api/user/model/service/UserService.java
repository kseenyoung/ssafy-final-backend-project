package com.ssafy.api.user.model.service;

import com.ssafy.api.user.model.UserLoginDto;
import com.ssafy.api.utils.MyException;

public interface UserService {
	
	public String[] jwtlogin(String name, String id);

    String login(UserLoginDto userLoginDto) throws MyException;
}
