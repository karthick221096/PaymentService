package com.karthick.paymentservice.controller;

import com.karthick.paymentservice.dtos.PaymentRequestDto;
import com.karthick.paymentservice.dtos.PaymentResponseDto;
import com.karthick.paymentservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponseDto> initiatePayment(@RequestBody PaymentRequestDto request) {
        PaymentResponseDto response = paymentService.initiatePayment(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(@RequestParam("orderId") String orderId) {
        paymentService.updatePaymentStatus(orderId);
        return ResponseEntity.ok("Payment successful for order: " + orderId);
    }
}
