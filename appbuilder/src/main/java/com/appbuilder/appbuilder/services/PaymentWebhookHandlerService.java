package com.appbuilder.appbuilder.services;

public interface PaymentWebhookHandlerService {

    void handlePaymentCompletedWebHook(String payload, String signature);
}
