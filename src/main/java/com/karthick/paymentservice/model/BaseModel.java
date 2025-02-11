package com.karthick.paymentservice.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class BaseModel {
    @GeneratedValue
    @Id
    private Long id;
    private Date createdAt;
    private Date updatedAt;
    private boolean deleted;
}
