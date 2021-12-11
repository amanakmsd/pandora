package com.api.pandora.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OTPVerificationResponse {
    private boolean status;
    private boolean verified;
}
