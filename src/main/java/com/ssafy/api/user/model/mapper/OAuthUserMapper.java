package com.ssafy.api.user.model.mapper;

import com.ssafy.api.user.model.OAuthUser;
import com.ssafy.api.user.model.UserLoginDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuthUserMapper {
    public String login(OAuthUser m);
    public void saveToken(OAuthUser m);
}
