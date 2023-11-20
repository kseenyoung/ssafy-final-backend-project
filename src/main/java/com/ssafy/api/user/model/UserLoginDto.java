package com.ssafy.api.user.model;

import com.ssafy.api.exception.MyException;
import com.ssafy.api.utils.RegEx;
import org.springframework.http.HttpStatus;

public class UserLoginDto {
    private String user_id, user_password;

    public UserLoginDto(String user_id, String user_password) throws MyException {
        setUser_id(user_id);
        setUser_password(user_password);
    }

    public UserLoginDto(){}

    public String getUser_id() {return user_id;}

    public void setUser_id(String user_id) throws MyException {
        if(user_id != null){
            RegEx.isUserId(user_id);
            this.user_id = user_id;
        } else{
            throw new MyException("유저 아이디를 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) throws MyException {
        if(user_password != null){
            RegEx.isUserPassword(user_password);
            this.user_password = user_password;
        } else{
            throw new MyException("유저 비밀번호를 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
    }

    public void setUser_encryptedPassword(String user_encryptedPassword) throws MyException{
        if(user_encryptedPassword == null){
            throw new MyException("암호화된 비밀번호는 비어있을 수 없습니다.", HttpStatus.NOT_ACCEPTABLE);
        }
        this.user_password = user_encryptedPassword;
    }

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "user_id='" + user_id + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }
}
