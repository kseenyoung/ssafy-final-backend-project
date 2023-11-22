package com.ssafy.api.plan.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.exception.MyException;
import com.ssafy.api.plan.model.PlanCreateDto;
import com.ssafy.api.plan.model.PlanDelete1Dto;
import com.ssafy.api.plan.model.UserListVO;
import com.ssafy.api.plan.model.service.PlanService;
import com.ssafy.api.user.model.UserLoginDto;
import com.ssafy.api.user.model.UserSession;
import com.ssafy.api.utils.SHAEncryption;
import com.ssafy.api.utils.HttpResponseBody;
import com.ssafy.api.utils.SHAEncryption;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
                case "delete1":
                    String user_id_delete1 = (String)body.get("user_id");
                    int plan_id_delete1 = (Integer)(body.get("plan_id"));
                    PlanDelete1Dto planDelete1Dto = new PlanDelete1Dto(user_id_delete1, plan_id_delete1);

                    // user_id와 plan_id가 연관이 있는지 확인
                    String checked_user_id = planService.delete1(planDelete1Dto);
                    String CSRF_token = UUID.randomUUID().toString();
                    session.setAttribute("CSRF_token", CSRF_token);

                    if(checked_user_id == null){
                        return new ResponseEntity<>(new HttpResponseBody<>("error", null), HttpStatus.NOT_ACCEPTABLE);
                    } else{
                        return new ResponseEntity<>(new HttpResponseBody<>("ok", CSRF_token), HttpStatus.OK);
                    }

                case "delete2":
                    String CSRF_token_delete2 = (String)body.get("CSRF_token");
                    if(!session.getAttribute("CSRF_token").equals(CSRF_token_delete2)){
                        throw new MyException("transaction id 오류", HttpStatus.NOT_ACCEPTABLE);
                    }

                    String user_password_delete2 = (String)body.get("user_password");
                    if(user_password_delete2 == null){
                        throw new MyException("패스워드 입력 필요함", HttpStatus.BAD_REQUEST);
                    }
                    UserSession userSession = (UserSession) session.getAttribute("userSession");
                    String salt = userSession.getSalt();
                    String encrypted_user_password = SHAEncryption.encrypt(user_password_delete2, salt);
                    String session_encrypted_user_password = userSession.getEncrypted_user_password();

                    if(!encrypted_user_password.equals(session_encrypted_user_password)){
                        // TODO: 해당 요청에 대해서 count 제한(5회)
                        throw new MyException("비밀번호 입력 오류", HttpStatus.NOT_ACCEPTABLE);
                    }

                    String plan_id = (String) body.get("plan_id");
                    if(plan_id == null){
                        throw new MyException("plan_id 입력 확인", HttpStatus.BAD_REQUEST);
                    }

                    int result = planService.delete2(plan_id);
                    if(result == 0){
                        return new ResponseEntity<>(new HttpResponseBody<>("삭제된 여행계획이 없습니다.", result), HttpStatus.NOT_ACCEPTABLE);
                    } else{
                        return new ResponseEntity<>(new HttpResponseBody<>("ok", result), HttpStatus.OK);
                    }

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
