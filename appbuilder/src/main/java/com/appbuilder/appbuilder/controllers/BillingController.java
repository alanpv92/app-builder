package com.appbuilder.appbuilder.controllers;

import com.appbuilder.appbuilder.dto.subscription.*;
import com.appbuilder.appbuilder.services.PlanService;
import com.appbuilder.appbuilder.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {

   private final PlanService planService;
   private final SubscriptionService subscriptionService;

   @GetMapping("/plans")
   public ResponseEntity<List<PlanResponseDto>> getAllPlans(){
       return ResponseEntity.ok(planService.getAllActivePlans());
   }

   @GetMapping("/me/subscription")
   public ResponseEntity<SubscriptionResponseDto> getMySubscription(){
       String dummyId="10";
       return ResponseEntity.ok(subscriptionService.getCurrentSubscription(dummyId));
   }

   @PostMapping("/checkout")
   public ResponseEntity<CheckOutResponseDto> checkout(@RequestBody CheckOutRequestDto checkOutRequestDto){
       String dummyId="10";
       return ResponseEntity.ok(subscriptionService.createCheckoutSessionUrl(checkOutRequestDto,dummyId));
   }

    @PostMapping("/portal")
    public ResponseEntity<PortalResponseDto> openCustomerPortal() {
        String userId = "10";
        return ResponseEntity.ok(subscriptionService.openCustomerPortal(userId));
    }

}
