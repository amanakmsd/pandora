package com.api.pandora.controller;

import com.api.pandora.model.request.PaymentRequest;
import com.api.pandora.model.response.PaymentResponse;
import com.api.pandora.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(
            value = "/pay",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PaymentResponse> postBody(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = paymentService.pay(paymentRequest);
        return ResponseEntity
                .created(URI
                        .create(String.format("PayeeContactNumber%s", paymentRequest.getPayeeContactNumber())))
                .body(paymentResponse);
    }
}

