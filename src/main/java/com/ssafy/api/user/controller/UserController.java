package com.ssafy.api.user.controller;

import com.ssafy.api.exception.MyException;
import com.ssafy.api.user.model.UserUpdateDto;
import com.ssafy.api.utils.HttpResponseBody;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.apache.ibatis.jdbc.Null;
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
    public ResponseEntity<HttpResponseBody<?>> user(@RequestBody Map<String, Object> body, HttpServletRequest request){
        String sign = (String)body.get("sign");
        ResponseEntity<HttpResponseBody<?>> response = null;
        HttpSession session = request.getSession(false);

        if(sign != null){
            System.out.println(sign);
            try {
                switch (sign){
                    case "logout":
                        session.invalidate();
                        break;

                    case "checkId":
                        String user_id_checkId = (String)body.get("user_id");
                        if(user_id_checkId == null){
                            throw new MyException("user_id를 입력해주세요", HttpStatus.BAD_REQUEST);
                        }
                        boolean result = userService.isUserIdDuplicate(user_id_checkId);

                        if(!result){
                            return new ResponseEntity<>(new HttpResponseBody<>("미중복", user_id_checkId), HttpStatus.OK);
                        } else{
                            return new ResponseEntity<>(new HttpResponseBody<>("중복", null), HttpStatus.BAD_REQUEST);
                        }

                    case "update":
                        String user_nickname = (String)body.get("user_nickname");
                        String user_email = (String)body.get("user_email");
//                        UserUpdateDto userUpdateDto = new UserUpdateDto(user_nickname, user_email);

                        break;
                    case "quit1":
                        break;
                    case "quit2":
                        break;
                    case "checkPassword":
                        break;
                    case "userSearch":
                        break;
                    case "modifyUserPassword":
                    default:
                        return new ResponseEntity<>(new HttpResponseBody("[ERROR] sign 값을 확인해주세요", null), HttpStatus.BAD_REQUEST);
                }

            } catch (MyException e){
                HttpResponseBody<String> responseBody = new HttpResponseBody<>("[ERROR]", e.getMessage());
                return new ResponseEntity<>(responseBody, e.getStatus());
            }
        } else{
            HttpResponseBody<String> responseBody = new HttpResponseBody<>("[ERROR]", "sign 값을 넣어주세요");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
