package com.ssafy.api.user.model;

public class UserSession {
    private String user_id, encrypted_user_password, user_key, salt;

    public UserSession() {}

    public UserSession(String user_id) {
        setUser_id(user_id);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEncrypted_user_password() {
        return encrypted_user_password;
    }

    public void setEncrypted_user_password(String encrypted_user_password) {
        this.encrypted_user_password = encrypted_user_password;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "user_id='" + user_id + '\'' +
                ", encrypted_user_password='" + encrypted_user_password + '\'' +
                ", user_key='" + user_key + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
