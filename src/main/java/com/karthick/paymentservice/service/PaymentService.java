package com.karthick.paymentservice.service;

import com.karthick.paymentservice.dtos.PaymentRequestDto;
import com.karthick.paymentservice.dtos.PaymentResponseDto;
import com.karthick.paymentservice.model.Payment;
import com.karthick.paymentservice.model.PaymentGateway;
import com.karthick.paymentservice.model.PaymentStatus;
import com.karthick.paymentservice.paymentGateway.PaymentLinkResult;
import com.karthick.paymentservice.paymentGateway.RazorpayPaymentGateway;
import com.karthick.paymentservice.paymentGateway.StripePayementGateway;
import com.karthick.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final StripePayementGateway stripeGateway;
    private final RazorpayPaymentGateway razorpayGateway;
    private final PaymentRepository paymentRepository;

    public PaymentService(StripePayementGateway stripeGateway,
                          RazorpayPaymentGateway razorpayGateway,
                          PaymentRepository paymentRepository) {
        this.stripeGateway = stripeGateway;
        this.razorpayGateway = razorpayGateway;
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponseDto initiatePayment(PaymentRequestDto request) {
        com.karthick.paymentservice.paymentGateway.PaymentGateway gateway = resolveGateway(request.getGateway());
        PaymentLinkResult result = gateway.initiatePayment(request);

        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setEmail(request.getEmail());
        payment.setPhoneNumber(request.getPhoneNumber());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setProductName(request.getProductName());
        payment.setGateway(request.getGateway());
        payment.setPaymentLinkId(result.getLinkId());
        payment.setPaymentLinkUrl(result.getLinkUrl());
        payment.setStatus(PaymentStatus.INITIATED);

        paymentRepository.save(payment);

        return new PaymentResponseDto(
                result.getLinkId(),
                result.getLinkUrl(),
                request.getGateway().name(),
                request.getOrderId()
        );
    }

    public void updatePaymentStatus(String orderId) {
        paymentRepository.findByOrderId(orderId).ifPresent(payment -> {
            payment.setStatus(PaymentStatus.SUCCESS);
            paymentRepository.save(payment);
        });
    }

    private com.karthick.paymentservice.paymentGateway.PaymentGateway resolveGateway(PaymentGateway gateway) {
        return switch (gateway) {
            case STRIPE -> stripeGateway;
            case RAZORPAY -> razorpayGateway;
        };
    }
}
