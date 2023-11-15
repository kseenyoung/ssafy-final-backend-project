package com.ssafy.api.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.api.user.model.Member;

@Mapper
public interface UserMapper {

	public String login(Member m);
	public void saveToken(Member m);
}
