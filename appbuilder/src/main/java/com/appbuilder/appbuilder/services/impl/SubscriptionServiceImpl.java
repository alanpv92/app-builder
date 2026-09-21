package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.subscription.CheckOutRequestDto;
import com.appbuilder.appbuilder.dto.subscription.CheckOutResponseDto;
import com.appbuilder.appbuilder.dto.subscription.PortalResponseDto;
import com.appbuilder.appbuilder.dto.subscription.SubscriptionResponseDto;
import com.appbuilder.appbuilder.entity.PlanEntity;
import com.appbuilder.appbuilder.entity.SubscriptionEntity;
import com.appbuilder.appbuilder.entity.UserEntity;
import com.appbuilder.appbuilder.entity.enums.SubscriptionStatus;
import com.appbuilder.appbuilder.exceptions.BadRequestException;
import com.appbuilder.appbuilder.exceptions.ServerException;
import com.appbuilder.appbuilder.repository.PlanRepository;
import com.appbuilder.appbuilder.repository.SubscriptionRepository;
import com.appbuilder.appbuilder.repository.UserRepository;
import com.appbuilder.appbuilder.services.SubscriptionService;
import com.appbuilder.appbuilder.utils.constants.ErrorMessageConstants;
import com.appbuilder.appbuilder.utils.helpers.SecurityHelper;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final PlanRepository planRepository;



     @Value("${stripe.api.redirect-url}")
     private String checkoutRedirectUrl;



    @Override
    public SubscriptionResponseDto getCurrentSubscription() {
        return null;
    }

    @Override
    public CheckOutResponseDto createCheckoutSessionUrl(CheckOutRequestDto checkOutRequestDto) {

        final String email= SecurityHelper.getEmail();

        SessionCreateParams sessionCreateParams=SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .setSuccessUrl(checkoutRedirectUrl + "/success.html?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(checkoutRedirectUrl + "/cancel.html")
                .setCustomerEmail(email)
                .addLineItem(
                SessionCreateParams.LineItem.builder()
                        .setPrice(checkOutRequestDto.getPlanId())
                        .setQuantity(1L)
                        .build())
                .build();
        try{
            Session session = Session.create(sessionCreateParams);
            return CheckOutResponseDto.builder()
                    .checkoutUrl(session.getUrl())
                    .build();

        }catch (Exception e){
            System.out.println(e.getMessage());
           throw new ServerException(ErrorMessageConstants.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public PortalResponseDto openCustomerPortal() {
        return null;
    }

    @Transactional
    @Override
    public void handleUserSubscription(String email, String customerId, String subscriptionId, String planId, LocalDateTime periodStart, LocalDateTime periodEnd,SubscriptionStatus subscriptionStatus) {


        //TODO check if subscription is already present then is there requirement of saving in case of session.completed event
       //user will be there because only a user can pay  and same for planId
       final UserEntity user=userRepository.findByEmail(email).orElseThrow(
               () -> new BadRequestException(ErrorMessageConstants.USER_NOT_FOUND)
       );

        final SubscriptionEntity subscriptionEntity=new SubscriptionEntity();
        final Optional<SubscriptionEntity> currentSubscription=subscriptionRepository.findSubscriptionByEmail(email);
        currentSubscription.ifPresent(entity -> subscriptionEntity.setId(entity.getId()));
        if(planId!=null){
            final PlanEntity plan=planRepository.findByStripePriceId(planId).orElseThrow(() -> new BadRequestException(ErrorMessageConstants.PLAN_NOT_FOUND));
            subscriptionEntity.setPlan(plan);
        }else{
            currentSubscription.ifPresent(entity -> subscriptionEntity.setPlan(entity.getPlan()));
        }


        if(periodStart!=null){
            subscriptionEntity.setCurrentPeriodStart(periodStart);
        }else{
            currentSubscription.ifPresent(entity -> subscriptionEntity.setCurrentPeriodStart(entity.getCurrentPeriodStart()));
        }

        if(periodEnd!=null){
            subscriptionEntity.setCurrentPeriodEnd(periodEnd);
        }else{
            currentSubscription.ifPresent(entity -> subscriptionEntity.setCurrentPeriodEnd(entity.getCurrentPeriodEnd()));
        }

       subscriptionEntity.setUser(user);
       subscriptionEntity.setStripeSubscriptionId(subscriptionId);
       subscriptionEntity.setStatus(subscriptionStatus);
       subscriptionEntity.setStripeCustomerId(customerId);
       subscriptionRepository.save(subscriptionEntity);

    }


}
