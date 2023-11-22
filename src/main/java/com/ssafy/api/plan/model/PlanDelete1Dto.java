package com.ssafy.api.plan.model;

import com.ssafy.api.exception.MyException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PlanDelete1Dto {
    private String user_id;
    private int plan_id;

    public PlanDelete1Dto() {}

    public PlanDelete1Dto(String user_id, int plan_id) throws MyException {
        setUser_id(user_id);
        setPlan_id(plan_id);
    }

    public void setUser_id(String user_id) throws MyException {
        if(user_id == null){
            throw new MyException("유저 아이디를 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        this.user_id = user_id;
    }

    public void setPlan_id(int plan_id) throws MyException {
        if(plan_id < 0){
            throw new MyException("잘못된 여행계획에대한 요청입니다.", HttpStatus.BAD_REQUEST);
        }
        this.plan_id = plan_id;
    }
}
