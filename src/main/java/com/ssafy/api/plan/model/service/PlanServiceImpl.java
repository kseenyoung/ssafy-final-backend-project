package com.ssafy.api.plan.model.service;

import com.ssafy.api.exception.MyException;
import com.ssafy.api.plan.model.PlanCreateDto;
import com.ssafy.api.plan.model.PlanDelete1Dto;
import com.ssafy.api.plan.model.UserListVO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssafy.api.plan.model.mapper.PlanMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    PlanMapper planMapper;

    @Override
    @Transactional
    public void create(PlanCreateDto planCreateDto) throws MyException {
        // TODO: 여행 계획 생성자와 멘션된 사람 따로 받아서 Message
        planMapper.createPlan(planCreateDto);
        planMapper.connectUserAndPlan(planCreateDto);
        planMapper.connectPlanAndAttractionInfo(planCreateDto);
    }

    @Override
    public List<UserListVO> userList(String userId) {
        return planMapper.selectUserList(userId);
    }

    @Override
    public String delete1(PlanDelete1Dto planDelete1Dto) {
        return planMapper.delete1(planDelete1Dto);
    }

    @Override
    @Transactional
    public int delete2(String plan_id) {
        System.out.println("plan_id : " + plan_id);
        int result1 = planMapper.delete2Plans(plan_id);
        int result2 = planMapper.delete2UserPlans(plan_id);
        return result1 + result2;
    }

}
