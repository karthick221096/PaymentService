package com.karthick.paymentservice.controller;

import com.karthick.paymentservice.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @GetMapping("/initiate/{orderId}")
    public String makePayment(@PathVariable("orderId") String orderId){
        return paymentService.initiatePayment(
                Long.valueOf(orderId),
                "872456784"
        );
    }
}
