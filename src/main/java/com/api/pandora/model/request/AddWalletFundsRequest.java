package com.api.pandora.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddWalletFundsRequest {
    private String userID;
    private String contactNumber;
    private int amount;
}
