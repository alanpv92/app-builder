package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.subscription.PlanResponseDto;
import com.appbuilder.appbuilder.services.PlanService;
import com.appbuilder.appbuilder.services.ProjectMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponseDto> getAllActivePlans() {
        return List.of();
    }
}
