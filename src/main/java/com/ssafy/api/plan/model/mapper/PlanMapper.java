package com.ssafy.api.plan.model.mapper;

import com.ssafy.api.plan.model.PlanCreateDto;
import com.ssafy.api.plan.model.UserListVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanMapper {

    void createPlan(PlanCreateDto planCreateDto);

    void connectUserAndPlan(PlanCreateDto planCreateDto);

    void connectPlanAndAttractionInfo(PlanCreateDto planCreateDto);

    List<UserListVO> selectUserList(String userId);
}
