package com.ssafy.api.user.model;

import lombok.Getter;

@Getter
public class UserUpdateDto {

    private String user_nickname, user_email;

    public void setUser_nickname(String user_nickname) {

        this.user_nickname = user_nickname;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
