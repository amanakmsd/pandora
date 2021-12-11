package com.api.pandora.service;

import com.api.pandora.model.request.PaymentRequest;
import com.api.pandora.model.response.PaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentResponse pay(PaymentRequest paymentRequest) {

        return new PaymentResponse("Transaction Sucessful", true);
    }
}
