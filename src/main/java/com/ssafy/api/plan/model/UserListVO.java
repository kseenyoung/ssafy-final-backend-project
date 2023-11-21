package com.ssafy.api.plan.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListVO {
    private String plan_id, title, plan_date, created_at, updated_at;
    private int hit;
    private List<String> users;
}
