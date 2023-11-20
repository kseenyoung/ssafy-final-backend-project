package com.ssafy.api.user.controller;

import com.ssafy.api.utils.HttpResponseBody;
import java.util.Map;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.user.model.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public ResponseEntity<HttpResponseBody<?>> user(@RequestBody Map<String, Object> body){
        String sign = (String)body.get("sign");
        ResponseEntity<HttpResponseBody<?>> response = null;

        if(sign != null){
            System.out.println(sign);
            try {
                switch (sign){
                    case "checkId":
                        String user_id_checkId = (String)body.get("user_id");
                        boolean result = userService.isUserIdDuplicate(user_id_checkId);

                        if(!result){
                            return new ResponseEntity<>(new HttpResponseBody<>("미중복", user_id_checkId), HttpStatus.OK);
                        } else{
                            return new ResponseEntity<>(new HttpResponseBody<>("중복", null), HttpStatus.BAD_REQUEST);
                        }
                    default:
                        return new ResponseEntity<>(new HttpResponseBody("[ERROR] sign 값을 확인해주세요", null), HttpStatus.BAD_REQUEST);
                }

            } catch (Exception e){
                HttpResponseBody<String> responseBody = new HttpResponseBody<>("[ERROR]", e.getMessage());
                response = new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            }
        } else{
            HttpResponseBody<String> responseBody = new HttpResponseBody<>("[ERROR]", "sign 값을 넣어주세요");
            response = new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
