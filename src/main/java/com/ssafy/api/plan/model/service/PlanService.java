package com.ssafy.api.plan.model.service;

import com.ssafy.api.exception.MyException;

public interface PlanService {
    void create(PlanCreateDto planCreateDto) throws MyException;
}
