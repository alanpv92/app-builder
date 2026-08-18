package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.subscription.PlanLimitsResponseDto;
import com.appbuilder.appbuilder.dto.subscription.UsageTodayResponseDto;
import com.appbuilder.appbuilder.services.UsageService;
import org.springframework.stereotype.Service;


@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponseDto getTodayUsageOfUser(String userId) {
        return null;
    }

    @Override
    public PlanLimitsResponseDto getCurrentSubscriptionLimitsOfUser(String userId) {
        return null;
    }
}
