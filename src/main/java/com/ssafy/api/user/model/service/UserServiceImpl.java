package com.ssafy.api.user.model.service;

import com.ssafy.api.user.model.UserJWTLoginDto;
import com.ssafy.api.user.model.UserLoginDto;
import com.ssafy.api.utils.MyException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.api.user.model.OAuthUser;
import com.ssafy.api.user.model.mapper.UserMapper;
import com.ssafy.api.utils.JwtTokenProvider;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public String[] jwtlogin(String name, String id) {
		// jwt 발급한 뒤 리턴
		// salt, access, refresh는 DB에 저장해야 함
		String salt = UUID.randomUUID().toString();
		String access_token = JwtTokenProvider.createAccessToken(name, salt);
		String refresh_token = JwtTokenProvider.createRefreshToken(id, salt);
		
		UserJWTLoginDto m = new UserJWTLoginDto();
		m.setUser_id(id);
		m.setSalt(salt);
		m.setAccess_token(access_token);
		m.setRefresh_token(access_token);
		
		userMapper.saveToken(m);
		
		return new String[] {access_token, refresh_token};
	}

	@Override
	public String login(UserLoginDto userLoginDto) throws MyException {
		return userMapper.login(userLoginDto);
	}
}
