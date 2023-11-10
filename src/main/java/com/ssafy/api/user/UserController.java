package com.ssafy.api.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@GetMapping("api")
	public ResponseEntity<?> api(){
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
