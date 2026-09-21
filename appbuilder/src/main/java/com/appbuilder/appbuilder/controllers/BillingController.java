package com.appbuilder.appbuilder.controllers;

import com.appbuilder.appbuilder.dto.subscription.*;
import com.appbuilder.appbuilder.services.PaymentWebhookHandlerService;
import com.appbuilder.appbuilder.services.PlanService;
import com.appbuilder.appbuilder.services.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {

   private final PlanService planService;
   private final SubscriptionService subscriptionService;
   private final PaymentWebhookHandlerService paymentWebhookHandlerService;

   @GetMapping("/plans")
   public ResponseEntity<List<PlanResponseDto>> getAllPlans(){
       return ResponseEntity.ok(planService.getAllActivePlans());
   }

   @PostMapping("/plans")
   public ResponseEntity<PlanResponseDto> createPlan(@RequestBody @Valid PlanRequestDto planRequestDto) {
       return ResponseEntity.status(HttpStatus.CREATED).body(planService.addPlan(planRequestDto));
   }

   @GetMapping("/me/subscription")
   public ResponseEntity<SubscriptionResponseDto> getMySubscription(){
       return ResponseEntity.ok(subscriptionService.getCurrentSubscription());
   }

   @PostMapping("/checkout")
   public ResponseEntity<CheckOutResponseDto> checkout(@RequestBody @Valid CheckOutRequestDto checkOutRequestDto){
       return ResponseEntity.ok(subscriptionService.createCheckoutSessionUrl(checkOutRequestDto));
   }

    @PostMapping("/portal")
    public ResponseEntity<PortalResponseDto> openCustomerPortal() {
        return ResponseEntity.ok(subscriptionService.openCustomerPortal());
    }

    @PostMapping("/webhook/payement-completed")
    public ResponseEntity<Void> handlePaymentCompletedWebHook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
      paymentWebhookHandlerService.handlePaymentCompletedWebHook(payload, sigHeader);
      return ResponseEntity.noContent().build();
    }

}
