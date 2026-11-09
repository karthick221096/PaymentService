package com.karthick.paymentservice.paymentGateway;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentLinkResult {
    private String linkId;
    private String linkUrl;
}
