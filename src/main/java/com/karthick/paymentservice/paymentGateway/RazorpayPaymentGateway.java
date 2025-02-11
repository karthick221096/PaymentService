package com.karthick.paymentservice.paymentGateway;

import com.stripe.exception.StripeException;

public class RazorpayPaymentGateway implements PaymentGateway{
    @Override
    public String initiatePayment(Long orderId, String phoneNumber) {
        return null;
    }
}
