package com.ssafy.api.user.model.service;

import com.ssafy.api.user.model.UserJWTLoginDto;
import com.ssafy.api.user.model.UserJoinDto;
import com.ssafy.api.user.model.UserLoginDto;
import com.ssafy.api.user.model.UserLoginVO;
import com.ssafy.api.user.model.UserSession;
import com.ssafy.api.utils.SHAEncryption;
import com.ssafy.api.exception.MyException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ssafy.api.user.model.mapper.UserMapper;
import com.ssafy.api.utils.JwtTokenProvider;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartException;

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
	public UserLoginVO login(UserLoginDto userLoginDto, UserSession userSession) throws MyException {
//		System.out.println(userLoginDto.getUser_id());
		String salt = userMapper.getSalt(userLoginDto.getUser_id());
		if(salt == null){
			throw new MyException("해당하는 유저가 없습니다.", HttpStatus.NOT_ACCEPTABLE);
		}

		String encryptedUserPassword = SHAEncryption.encrypt(userLoginDto.getUser_password(), salt);
		userLoginDto.setUser_encryptedPassword(encryptedUserPassword);
		userSession.setEncrypted_user_password(encryptedUserPassword);

		UserLoginVO userLoginVO = userMapper.login(userLoginDto);
		String user_key = UUID.randomUUID().toString();
		userLoginVO.setUser_key(user_key);
		userSession.setUser_key(user_key);
		userSession.setSalt(salt);

		return userLoginVO;
	}

	@Override
	public boolean isUserIdDuplicate(String userId) throws MyException {
		String user_id = userMapper.isUserIdDuplicate(userId);
        return user_id != null;
    }

	@Override
	@Transactional
	public void join(UserJoinDto userJoinDto) throws MyException {
		String salt = SHAEncryption.genSalt();
		String encryptedPassword = SHAEncryption.encrypt(userJoinDto.getUser_password(), salt);

		userJoinDto.setUser_encryptedPassword(encryptedPassword);
		userJoinDto.setSalt(salt);

		userMapper.join(userJoinDto);
		userMapper.setSalt(userJoinDto);
	}

	@Override
	public String email(String user_id) throws MyException {
		String email = userMapper.email(user_id);
		if(email == null){
			throw new MyException("유저의 이메일이 없음", HttpStatus.NOT_ACCEPTABLE);
		}
		return email;
	}
}
