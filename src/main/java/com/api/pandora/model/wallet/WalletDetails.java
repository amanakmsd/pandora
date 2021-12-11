package com.api.pandora.model.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WalletDetails {
    private String userID;
    private String contactNumber;
    private int walletAmount;
}
