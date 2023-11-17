package com.ssafy.api.user.model;

import com.ssafy.api.utils.MyException;
import com.ssafy.api.utils.RegEx;
import org.springframework.http.HttpStatus;

public class UserJoinDto {
    private String user_id, user_password, user_name, user_nickname, user_email;

    public UserJoinDto() {}

    public UserJoinDto(String user_id, String user_password, String user_name, String user_nickname,
                       String user_email) throws MyException {
        setUser_id(user_id);
        setUser_name(user_name);
        setUser_email(user_email);
        setUser_password(user_password);
        setUser_nickname(user_nickname);
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) throws MyException {
        if(user_name == null){
            throw new MyException("이름을 입력 해주세요", HttpStatus.BAD_REQUEST);
        }
        this.user_name = user_name;
    }

    public String getUser_id() {return user_id;}

    public void setUser_id(String user_id) throws MyException {
        if(user_id == null){
            throw new MyException("유저 아이디를 작성해주세요", HttpStatus.BAD_REQUEST);
        }
        RegEx.isUserId(user_id);
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) throws MyException {
        if(user_password == null){
            throw new MyException("유저 비밀번호를 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        RegEx.isUserPassword(user_password);
        this.user_password = user_password;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) throws MyException {
        if(user_nickname == null){
            throw new MyException("유저 닉네임을 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
        RegEx.isUserNickname(user_nickname);
        this.user_nickname = user_nickname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) throws MyException {
        RegEx.isUserEmail(user_email);
        this.user_email = user_email;
    }

    @Override
    public String toString() {
        return "UserJoinDto{" +
                "user_id='" + user_id + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_email='" + user_email + '\'' +
                '}';
    }
}
