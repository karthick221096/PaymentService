package com.karthick.paymentservice.paymentGateway;

import com.karthick.paymentservice.dtos.PaymentRequestDto;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("razorpayGateway")
public class RazorpayPaymentGateway implements PaymentGateway {

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Override
    public PaymentLinkResult initiatePayment(PaymentRequestDto request) {
        try {
            RazorpayClient client = new RazorpayClient(apiKey, apiSecret);

            JSONObject customer = new JSONObject();
            customer.put("email", request.getEmail());
            customer.put("contact", request.getPhoneNumber());

            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);

            JSONObject notes = new JSONObject();
            notes.put("orderId", request.getOrderId());

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", request.getAmount());
            paymentLinkRequest.put("currency", request.getCurrency());
            paymentLinkRequest.put("description", request.getProductName());
            paymentLinkRequest.put("customer", customer);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("notes", notes);
            paymentLinkRequest.put("reminder_enable", true);
            paymentLinkRequest.put("callback_url",
                    "http://localhost:8282/orders/confirm?orderId=" + request.getOrderId());
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink paymentLink = client.paymentLink.create(paymentLinkRequest);
            return new PaymentLinkResult(paymentLink.get("id"), paymentLink.get("short_url"));

        } catch (RazorpayException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
