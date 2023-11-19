package com.ssafy.api.user.model.service;

import com.ssafy.api.user.model.UserJWTLoginDto;
import com.ssafy.api.user.model.UserJoinDto;
import com.ssafy.api.user.model.UserLoginDto;
import com.ssafy.api.user.model.UserLoginVO;
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
	public String[] jwtlogin(String user_id, String user_nickname) {
		// jwt 발급한 뒤 리턴
		// salt, access, refresh는 DB에 저장해야 함
		String salt = UUID.randomUUID().toString();
		String access_token = JwtTokenProvider.createAccessToken(user_id, salt);
		String refresh_token = JwtTokenProvider.createRefreshToken(user_nickname, salt);
		
		UserJWTLoginDto m = new UserJWTLoginDto();
		m.setUser_id(user_id);
		m.setSalt(salt);
		m.setAccess_token(access_token);
		m.setRefresh_token(access_token);
		
		userMapper.saveToken(m);
		
		return new String[] {access_token, refresh_token, salt};
	}

	@Override
	public UserLoginVO login(UserLoginDto userLoginDto) throws MyException {
		UserLoginVO userLoginVO = userMapper.login(userLoginDto);
		userLoginVO.setUser_key(UUID.randomUUID().toString());
		return userLoginVO;
	}

	@Override
	public boolean isUserIdDuplicate(String userId) throws MyException {
        return userMapper.isUserIdDuplicate(userId) != null;
    }

	@Override
	public void join(UserJoinDto userJoinDto) throws MyException {
		userMapper.join(userJoinDto);
	}
}
