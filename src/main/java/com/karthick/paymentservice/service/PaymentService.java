package com.karthick.paymentservice.service;

import com.karthick.paymentservice.dtos.PaymentRequestDto;
import com.karthick.paymentservice.dtos.PaymentResponseDto;
import com.karthick.paymentservice.model.PaymentGateway;
import com.karthick.paymentservice.paymentGateway.RazorpayPaymentGateway;
import com.karthick.paymentservice.paymentGateway.StripePayementGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final StripePayementGateway stripeGateway;
    private final RazorpayPaymentGateway razorpayGateway;

    public PaymentService(StripePayementGateway stripeGateway,
                          RazorpayPaymentGateway razorpayGateway) {
        this.stripeGateway = stripeGateway;
        this.razorpayGateway = razorpayGateway;
    }

    public PaymentResponseDto initiatePayment(PaymentRequestDto request) {
        com.karthick.paymentservice.paymentGateway.PaymentGateway gateway = resolveGateway(request.getGateway());
        String paymentLink = gateway.initiatePayment(request);
        return new PaymentResponseDto(paymentLink, request.getGateway().name(), request.getOrderId());
    }

    private com.karthick.paymentservice.paymentGateway.PaymentGateway resolveGateway(PaymentGateway gateway) {
        return switch (gateway) {
            case STRIPE -> stripeGateway;
            case RAZORPAY -> razorpayGateway;
        };
    }
}
