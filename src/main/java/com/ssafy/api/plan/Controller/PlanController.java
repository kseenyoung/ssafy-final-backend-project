package com.ssafy.api.plan.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.exception.MyException;
import com.ssafy.api.plan.model.PlanCreateDto;
import com.ssafy.api.plan.model.UserListVO;
import com.ssafy.api.plan.model.service.PlanService;
import com.ssafy.api.user.model.UserLoginDto;
import com.ssafy.api.utils.HttpResponseBody;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @PostMapping("")
    public ResponseEntity<HttpResponseBody<?>> plan(@RequestBody Map<String, Object> body, HttpServletRequest request){
        String sign = (String)body.get("sign");
        ResponseEntity<HttpResponseBody<?>> response = null;
        ObjectMapper om = new ObjectMapper();
        HttpSession session = request.getSession(false);

        if(sign != null){
            System.out.println(sign);
            try{
            switch (sign){
                case "create":
                    UserLoginDto userLoginDto = (UserLoginDto) session.getAttribute("userLoginDto");
                    System.out.println("[plan create] " + userLoginDto);
                    String title = (String) body.get("title");
                    String plan_date = (String) body.get("plan_date");
                    List<String> content_ides = (List<String>) body.get("content_ides");
                    List<String> users = (List<String>) body.get("users");
                    PlanCreateDto planCreateDto = new PlanCreateDto(title, plan_date, content_ides, users);
//                    System.out.println(planCreateDto);

                    planService.create(planCreateDto);

                    HttpResponseBody<String> responseBody = new HttpResponseBody<>("ok", "여행 등록 완료");
                    return new ResponseEntity<>(responseBody, HttpStatus.OK);

                case "userList":
                    String user_id = (String)body.get("user_id");
                    if(user_id == null){
                        throw new MyException("조회 할 user_id를 입력해주세요", HttpStatus.BAD_REQUEST);
                    }

                    List<UserListVO> userListVOS = planService.userList(user_id);

                    return new ResponseEntity<>(new HttpResponseBody<>("ok", userListVOS), HttpStatus.OK);

                case "allList":
                    break;
                case "addUser":
                    break;
                case "detail":
                    break;
                case "delete":
                    break;
                case "modify":
                    break;
            }

            } catch (Exception e){
                HttpResponseBody<String> responseBody = new HttpResponseBody<>("Error", e.getMessage());
                response = new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            }
        } else{

        }

        return response;

    }

}
