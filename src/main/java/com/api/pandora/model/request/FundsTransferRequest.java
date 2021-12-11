package com.api.pandora.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class FundsTransferRequest {
    private String userId;
    private String payeeMobileNumber;
    private String payerMobileNumber;
    private int transferRequestAmount;
}
