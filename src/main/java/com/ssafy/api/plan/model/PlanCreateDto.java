package com.ssafy.api.plan.model;

import com.ssafy.api.exception.MyException;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class PlanCreateDto {
    @Setter
    private int plan_id;
    private String title, plan_date;
    private List<String> content_ides, users;

    public PlanCreateDto() {}

    public PlanCreateDto(String title, String plan_date, List<String> content_ides, List<String> users) throws MyException {
        setTitle(title);
        setPlan_date(plan_date);
        setContent_ides(content_ides);
        setUsers(users);
    }

    public void setTitle(String title) throws MyException {
        if(title == null){
            throw new MyException("title을 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        this.title = title;
    }

    public void setPlan_date(String plan_date) throws MyException {
        if(plan_date == null){
            throw new MyException("해당하는 여행계획의 날짜를 입력해주세요", HttpStatus.BAD_REQUEST);
        }
        this.plan_date = plan_date;
    }

    public void setContent_ides(List<String> content_ides) throws MyException {
        if(content_ides == null){
            throw new MyException("관광지 정보 객체가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        this.content_ides = content_ides;
    }

    public void setUsers(List<String> users) throws MyException {
        if(users == null || users.isEmpty()){
            throw new MyException("여행계획에 포함됨 사람이 없습니다.", HttpStatus.BAD_REQUEST);
        }
        this.users = users;
    }
}
