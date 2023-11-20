package com.ssafy.api.plan.model.service;

import com.ssafy.api.exception.MyException;
import com.ssafy.api.plan.model.PlanCreateDto;

public interface PlanService {
    void create(PlanCreateDto planCreateDto) throws MyException;
}
