package com.appbuilder.appbuilder.services;

import com.appbuilder.appbuilder.dto.subscription.PlanResponseDto;

import java.util.List;

public interface PlanService {

    List<PlanResponseDto> getAllActivePlans();
}
