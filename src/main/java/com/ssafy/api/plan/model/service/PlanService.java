package com.ssafy.api.plan.model.service;

import com.ssafy.api.exception.MyException;
import com.ssafy.api.plan.model.PlanCreateDto;
import com.ssafy.api.plan.model.PlanDelete1Dto;
import com.ssafy.api.plan.model.UserListVO;
import java.util.List;

public interface PlanService {
    void create(PlanCreateDto planCreateDto) throws MyException;

    List<UserListVO> userList(String userId);

    String delete1(PlanDelete1Dto planDelete1Dto);

    int delete2(String plan_id);
}
