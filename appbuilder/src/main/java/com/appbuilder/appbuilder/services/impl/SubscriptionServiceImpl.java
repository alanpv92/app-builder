package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.subscription.CheckOutRequestDto;
import com.appbuilder.appbuilder.dto.subscription.CheckOutResponseDto;
import com.appbuilder.appbuilder.dto.subscription.PortalResponseDto;
import com.appbuilder.appbuilder.dto.subscription.SubscriptionResponseDto;
import com.appbuilder.appbuilder.services.SubscriptionService;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponseDto getCurrentSubscription(String userId) {
        return null;
    }

    @Override
    public CheckOutResponseDto createCheckoutSessionUrl(CheckOutRequestDto checkOutRequestDto, String userId) {
        return null;
    }

    @Override
    public PortalResponseDto openCustomerPortal(String userId) {
        return null;
    }
}
