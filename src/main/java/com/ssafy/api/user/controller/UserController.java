package com.ssafy.api.user.controller;

import com.ssafy.api.user.model.UserLoginDto;
import com.ssafy.api.utils.MyException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.api.user.model.service.OAuthService;
import com.ssafy.api.user.model.service.UserService;
import org.springframework.web.servlet.function.ServerRequest.Headers;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	OAuthService oAuthService;
	
	@PostMapping("")
	public ResponseEntity<?> user(@RequestBody Map<String, String> body, HttpServletRequest request){
		ResponseEntity response = null;
		String sign = body.get("sign");
		String user_agent = request.getHeader("User-Agent");
		System.out.println("body : " + body);
		HttpSession session = null;

		try{
			if(sign != null) {
				System.out.println(sign);

				if("login".equals(sign)){
					session = request.getSession(false);
					if(session != null){
						throw new MyException("이미 로그인 되어 있습니다.", HttpStatus.BAD_REQUEST);
					}
					String user_id = body.get("user_id");
					String user_password = body.get("user_password");
					UserLoginDto userLoginDto = new UserLoginDto(user_id, user_password);
					System.out.println(userLoginDto);

					String user_nickname = userService.login(userLoginDto);
					if(user_nickname != null){  // 로그인 성공
						session = request.getSession();
						session.setAttribute("userLoginDto", userLoginDto);
						session.setAttribute("user_agent", user_agent);
						return new ResponseEntity<String>("login ok", HttpStatus.OK);
					} else{
						throw new MyException("해당하는 회원이 없습니다.", HttpStatus.BAD_REQUEST);
					}

				}

			} else {
				// sign 값이 없음
				response = new ResponseEntity<String>("sign 값을 입력해주세요", HttpStatus.BAD_REQUEST);
			}
		} catch (MyException e){
			e.printStackTrace();
			response = new ResponseEntity<String>(e.getMessage(), e.getStatus());
		}

		
		
		return response;
	}
	

	@PostMapping("jwtlogin")
	public Map<String, Object> jwtlogin(@RequestBody Map<String, String> reqMap){
		System.out.println(reqMap);
		// DB
		String name = "김신영";
		String id = reqMap.get("id");
		if(name != null) {  // login ok - token 발행
			String[] tokens = userService.jwtlogin(name, id);
			if(tokens.length > 0) {  // ok
				Map<String, Object> resMap = new HashMap();
				resMap.put("tokens", tokens);
				return resMap;
			} else { // login Ok, token Fail..
				//why token doesn't work
				System.out.println("토큰 발행이 원활하지 않음");
				return null;
			}
		}
		return null;
	}
	
	@GetMapping("kakaologin")
	public Map<String, Object> kakaologin(@RequestParam String code) {
		System.out.println("kakao login");
		String access_Token = oAuthService.getKaKaoAccessToken(code);  // getKakaoAccessToken에 보내주는 것은 web browser의 '인가 코드'이다.
		String email=oAuthService.createKakaoUser(access_Token);
		
		if(email!=null) {
			System.out.println("userController :" + email);
			//db...
			// ***Session 으로 저장하기 위해서 해야하는 구간
//			   HttpSession session=request.getSession();
//			   session.setAttribute("email", email);
//			   Cookie c=new Cookie("id",email);
//	       c.setHttpOnly(true);
//			   response.addCookie(c);
			// ***Session 마무리
			   
			   //***JWT start
//				String[] tokens = userService.jwtlogin(email, email);  // id를 가져오지 않아서 둘 다 email로 token을 생성하려 함
//				if(tokens.length > 0) {  // ok
			Map<String, Object> resMap = new HashMap<>();
			resMap.put("tokens", "ok");
//					Map<String, Object> resMap = new HashMap();
//					resMap.put("tokens", tokens);
//					return resMap;
//				} else { // login Ok, token Fail..
//					//why token doesn't work
//					System.out.println("토큰 발행이 원활하지 않음");
//					return null;
//				}
			   //***JWT end
			   
				//Kakao 동의만으로 끝낼 것인지 우리만의 보안을 더 넣을 것인지 고민해야 한다.
			   // 가지고 들어온 jwt또는 session ID를 통해서 로그인 하고 있음을 판단하는 코드가 필요하다.
		   }
		return null;
	}
	
}
