package com.api.pandora.model.request;

import lombok.Data;

@Data
public class OTPVerificationRequest {
    private String otp;
    private String contactNumber;
}