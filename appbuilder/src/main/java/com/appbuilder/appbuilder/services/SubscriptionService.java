package com.appbuilder.appbuilder.services;

import com.appbuilder.appbuilder.dto.subscription.CheckOutRequestDto;
import com.appbuilder.appbuilder.dto.subscription.CheckOutResponseDto;
import com.appbuilder.appbuilder.dto.subscription.PortalResponseDto;
import com.appbuilder.appbuilder.dto.subscription.SubscriptionResponseDto;
import com.appbuilder.appbuilder.entity.SubscriptionEntity;
import com.appbuilder.appbuilder.entity.enums.SubscriptionStatus;

import java.time.LocalDateTime;

public interface SubscriptionService {

    SubscriptionResponseDto getCurrentSubscription();

    CheckOutResponseDto createCheckoutSessionUrl(CheckOutRequestDto checkOutRequestDto);

    PortalResponseDto openCustomerPortal();

    void handleUserSubscription(String email, String customerId, String subscriptionId, String planId, LocalDateTime periodStart, LocalDateTime periodEnd, SubscriptionStatus subscriptionStatus);
}
