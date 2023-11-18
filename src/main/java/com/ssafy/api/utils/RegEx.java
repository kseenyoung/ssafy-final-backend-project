package com.ssafy.api.utils;

import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;

public class RegEx {
    // 아이디는 5글자 이상, 15글자 이하여야 하고 영문소문자와 숫자만 가능하다.
    public static void isUserId(String user_id) throws MyException{
        if(!Pattern.matches("^[a-z0-9]{5,15}$", user_id)){
            throw new MyException("유저 아이디 정규표현식을 따라주세요.", HttpStatus.BAD_REQUEST);
        }
    }

    // 비밀번호는 8글자 이상이어야 하며 특수문자를 포함해야한다.
    public static void isUserPassword(String user_password) throws MyException{
        if(!Pattern.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,15}$", user_password)){
            throw new MyException("유저 비밀번호 정규표현식을 따라주세요.", HttpStatus.BAD_REQUEST);
        }
    }

    // 닉네임은 2글자 이상, 8글자 이하여야 하며 특수문자는 사용할 수 없어요
    public static void isUserNickname(String user_nickname) throws MyException{
        if(!Pattern.matches("^[가-힣a-zA-Z0-9]{2,8}$", user_nickname)){
            throw new MyException("유저 닉네임 정규표현식을 따라주세요.", HttpStatus.BAD_REQUEST);
        }
    }

    public  static void isUserEmail(String user_email) throws MyException{
        if(!Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", user_email)){
            throw new MyException("알맞는 이메일 표현으로 작성해주세요.", HttpStatus.BAD_REQUEST);
        }
    }

}
