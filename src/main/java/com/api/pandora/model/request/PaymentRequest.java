package com.api.pandora.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    int otp;
    String payeeContactNumber;
    String payerContactNumber;
    int amount;
}


