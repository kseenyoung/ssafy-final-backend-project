package com.ssafy.api.user.model.mapper;

import com.ssafy.api.user.model.UserJWTLoginDto;
import com.ssafy.api.user.model.UserJoinDto;
import com.ssafy.api.user.model.UserLoginDto;
import com.ssafy.api.user.model.UserLoginVO;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.api.user.model.OAuthUser;

@Mapper
public interface UserMapper {

	public UserLoginVO login(UserLoginDto m);
	public String jwtLogin(UserJWTLoginDto m);
	public void saveToken(UserJWTLoginDto m);
	String isUserIdDuplicate(String userId);
	void join(UserJoinDto userJoinDto);
}
