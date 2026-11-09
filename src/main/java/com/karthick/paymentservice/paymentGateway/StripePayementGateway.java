package com.karthick.paymentservice.paymentGateway;

import com.karthick.paymentservice.dtos.PaymentRequestDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("stripeGateway")
public class StripePayementGateway implements PaymentGateway {

    @Value("${stripe.api.key}")
    private String apiKey;

    @Override
    public PaymentLinkResult initiatePayment(PaymentRequestDto request) {
        Stripe.apiKey = apiKey;

        try {
            PriceCreateParams priceParams = PriceCreateParams.builder()
                    .setCurrency(request.getCurrency().toLowerCase())
                    .setUnitAmount(request.getAmount())
                    .setProductData(
                            PriceCreateParams.ProductData.builder()
                                    .setName(request.getProductName())
                                    .build()
                    )
                    .build();

            Price price = Price.create(priceParams);

            PaymentLinkCreateParams linkParams = PaymentLinkCreateParams.builder()
                    .addLineItem(
                            PaymentLinkCreateParams.LineItem.builder()
                                    .setPrice(price.getId())
                                    .setQuantity(1L)
                                    .build()
                    )
                    .putMetadata("orderId", request.getOrderId())
                    .setAfterCompletion(
                            PaymentLinkCreateParams.AfterCompletion.builder()
                                    .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                    .setRedirect(
                                            PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                    .setUrl("http://localhost:8282/orders/confirm?orderId=" + request.getOrderId())
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            PaymentLink paymentLink = PaymentLink.create(linkParams);
            return new PaymentLinkResult(paymentLink.getId(), paymentLink.getUrl());

        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
