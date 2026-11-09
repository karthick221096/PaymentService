package com.karthick.paymentservice.paymentGateway;

import com.karthick.paymentservice.dtos.PaymentRequestDto;

public interface PaymentGateway {
    PaymentLinkResult initiatePayment(PaymentRequestDto request);
}
