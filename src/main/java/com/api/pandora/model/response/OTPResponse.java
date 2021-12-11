package com.api.pandora.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OTPResponse {
    private boolean status;
    private String messageID;
}
