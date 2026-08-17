package com.appbuilder.appbuilder.services;

import com.appbuilder.appbuilder.dto.subscription.CheckOutRequestDto;
import com.appbuilder.appbuilder.dto.subscription.CheckOutResponseDto;
import com.appbuilder.appbuilder.dto.subscription.PortalResponseDto;
import com.appbuilder.appbuilder.dto.subscription.SubscriptionResponseDto;

public interface SubscriptionService {

    SubscriptionResponseDto getCurrentSubscription(String userId);

    CheckOutResponseDto createCheckoutSessionUrl(CheckOutRequestDto checkOutRequestDto, String userId);

    PortalResponseDto openCustomerPortal(String userId);
}
