package com.karthick.paymentservice.dtos;

import com.karthick.paymentservice.model.PaymentGateway;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {
    private String orderId;
    private String email;
    private String phoneNumber;
    private Long amount;
    private String currency;
    private String productName;
    private PaymentGateway gateway;
}
