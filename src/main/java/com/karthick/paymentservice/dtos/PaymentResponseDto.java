package com.karthick.paymentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResponseDto {
    private String paymentLink;
    private String gateway;
    private String orderId;
}
