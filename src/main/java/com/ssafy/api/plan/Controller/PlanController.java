package com.ssafy.api.plan.Controller;

import com.ssafy.api.plan.model.service.PlanService;
import com.ssafy.api.utils.HttpResponseBody;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("plan")
public class PlanController {

    @Autowired
    PlanService planService;

    @PostMapping("")
    public ResponseEntity<HttpResponseBody<?>> plan(@RequestBody Map<String, String> request){
        String sign = request.get("sign");
        ResponseEntity<HttpResponseBody<?>> response = null;

        if(sign != null){
            switch (sign){
                case "create":
                    break;
                case "userList":
                    break;
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

        } else{

        }

        return response;

    }

}
