package com.ssafy.api.user.model;

import lombok.Getter;
import lombok.Setter;

public class UserLoginVO {

    @Getter
    @Setter
    private String user_id, user_nickname, user_key;

    public UserLoginVO() {}

    public UserLoginVO(String user_id, String user_nickname, String user_key) {
        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_key = user_key;
    }
}
