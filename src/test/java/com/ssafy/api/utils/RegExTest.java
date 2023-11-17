package com.ssafy.api.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegExTest {

    @Test
    void isUserEmail() {
        String user_email = "ekfjwke@jekfj";
        Assertions.assertTrue(Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", user_email));
    }
}