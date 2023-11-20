package com.ssafy.api.plan.model.service;

import com.ssafy.api.exception.MyException;
import org.apache.ibatis.annotations.Param;
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
        planMapper.createPlan(planCreateDto);
        planMapper.connectUserAndPlan(planCreateDto);
        planMapper.connectPlanAndAttractionInfo(planCreateDto);
    }
}
