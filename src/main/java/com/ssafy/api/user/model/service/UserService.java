package com.ssafy.api.user.model.service;

import com.ssafy.api.user.model.UserJoinDto;
import com.ssafy.api.user.model.UserLoginDto;
import com.ssafy.api.user.model.UserLoginVO;
import com.ssafy.api.exception.MyException;

public interface UserService {
	
	public String[] jwtlogin(String user_id, String user_name);

    UserLoginVO login(UserLoginDto userLoginDto) throws MyException;

    boolean isUserIdDuplicate(String userId) throws MyException;

    void join(UserJoinDto userJoinDto) throws MyException;
}
