package com.karthick.paymentservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment extends BaseModel {

    private String orderId;
    private String email;
    private String phoneNumber;
    private Long amount;
    private String currency;
    private String productName;

    private String paymentLinkId;
    private String paymentLinkUrl;

    @Enumerated(EnumType.STRING)
    private PaymentGateway gateway;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
