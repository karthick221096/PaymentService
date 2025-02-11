package com.karthick.paymentservice.service;

import com.karthick.paymentservice.paymentGateway.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }


    public String initiatePayment(Long orderId, String phoneNumber){
        return paymentGateway.initiatePayment(
                orderId,
                phoneNumber
        );
    }

}
