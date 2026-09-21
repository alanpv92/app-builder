package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.entity.SubscriptionEntity;
import com.appbuilder.appbuilder.entity.enums.SubscriptionStatus;
import com.appbuilder.appbuilder.exceptions.BadRequestException;
import com.appbuilder.appbuilder.services.PaymentWebhookHandlerService;
import com.appbuilder.appbuilder.services.SubscriptionService;
import com.appbuilder.appbuilder.utils.constants.ErrorMessageConstants;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
class PaymentWebhookHandlerServiceImpl implements PaymentWebhookHandlerService {

    @Value("${stripe.webhook.secret}")
    private String stripeWebhookSecret;

    private final SubscriptionService subscriptionService;





    @Override
    public void handlePaymentCompletedWebHook(String payload, String signature) {
        Event event;
        try {
            event = Webhook.constructEvent(payload, signature, stripeWebhookSecret);
        } catch (SignatureVerificationException e) {
            System.err.println("Signature verification failed: " + e.getMessage());
            throw new BadRequestException(ErrorMessageConstants.WEB_HOOK_INTERNAL_ERROR);
        } catch (Exception e) {
            System.err.println("Error parsing webhook: " + e.getMessage());
            throw new BadRequestException(ErrorMessageConstants.WEB_HOOK_INTERNAL_ERROR);
        }

        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        if (dataObjectDeserializer.getObject().isEmpty()) {
            System.err.println("Deserialization failed for event: " + event.getType());
            return;
        }

        StripeObject stripeObject = dataObjectDeserializer.getObject().get();

        switch (event.getType()) {

            case "checkout.session.completed":
                if (stripeObject instanceof Session session) {
                    handleCheckoutSessionCompleted(session);
                }
                break;

            case "invoice.paid":
                if (stripeObject instanceof Invoice invoice) {
                    invoice.getLines().getData().getFirst().getPricing().getPriceDetails().getPrice();
                    handleInvoicePaid(invoice);
                }
                break;



        //TODO HANDLE BELOW EVENT IN THE FUTURE

//            // Subscription Initialized / Created
//            case "customer.subscription.created":
//                if (stripeObject instanceof Subscription subscription) {
//                    handleSubscriptionCreated(subscription);
//                }
//                break;
//
//

//
//            // Subscription Updated (Upgrades, Downgrades, Status Shifts)
//            case "customer.subscription.updated":
//                if (stripeObject instanceof Subscription subscription) {
//                    handleSubscriptionUpdated(subscription);
//                }
//                break;
//
//            // Recurring Payment Failed
//            case "invoice.payment_failed":
//                if (stripeObject instanceof Invoice invoice) {
//                    handleInvoicePaymentFailed(invoice);
//                }
//                break;
//
//            // SCA / 3DS Action Required
//            case "invoice.payment_action_required":
//                if (stripeObject instanceof Invoice invoice) {
//                    handlePaymentActionRequired(invoice);
//                }
//                break;
//
//            // Trial Ending Soon (3 days prior)
//            case "customer.subscription.trial_will_end":
//                if (stripeObject instanceof Subscription subscription) {
//                    handleTrialWillEnd(subscription);
//                }
//                break;
//
//            // Subscription Canceled or Ended (Revoke Access)
//            case "customer.subscription.deleted":
//                if (stripeObject instanceof Subscription subscription) {
//                    handleSubscriptionDeleted(subscription);
//                }
//                break;

            default:
                System.out.println("Unhandled event type: " + event.getType());
                break;
        }
    }





    private void handleCheckoutSessionCompleted(Session session) {
        String email = session.getCustomerDetails().getEmail();
        String customerId = session.getCustomer();
        String subscriptionId = session.getSubscription();

        subscriptionService.handleUserSubscription(email,customerId,subscriptionId,null,null,null, SubscriptionStatus.INCOMPLETE);
    }


    private void handleInvoicePaid(Invoice invoice) {
        String email=invoice.getCustomerEmail();
        String customerId = invoice.getCustomer();
        String subscriptionId=invoice.getLines().getData().getFirst().getSubscription();
        String planId=invoice.getLines().getData().getFirst().getPricing().getPriceDetails().getPrice();
        LocalDateTime periodStart = LocalDateTime.ofInstant(Instant.ofEpochSecond(invoice.getPeriodStart()), ZoneId.of("UTC"));
        LocalDateTime periodEnd = LocalDateTime.ofInstant(Instant.ofEpochSecond(invoice.getPeriodEnd()), ZoneId.of("UTC"));
        subscriptionService.handleUserSubscription(email,customerId,subscriptionId,planId,periodStart,periodEnd,SubscriptionStatus.ACTIVE);
    }

}