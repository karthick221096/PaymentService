package com.karthick.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseModel {
    private String orderId;
    private String email;
    private String phoneNumber;
    private Long amount;
    private String currency;
    private String productName;
}
