package com.api.pandora.model.response;

import com.api.pandora.model.wallet.WalletDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddWalletFundsResponse {
    private boolean fundsAdded;
    private WalletDetails walletDetails;
}
