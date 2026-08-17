package com.appbuilder.appbuilder.services;

import com.appbuilder.appbuilder.dto.subscription.PlanLimitsResponseDto;
import com.appbuilder.appbuilder.dto.subscription.UsageTodayResponseDto;

public interface UsageService {
    UsageTodayResponseDto getTodayUsageOfUser(String userId);

    PlanLimitsResponseDto getCurrentSubscriptionLimitsOfUser(String userId);
}
