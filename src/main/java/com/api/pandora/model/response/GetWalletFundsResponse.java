package com.api.pandora.model.response;

import com.api.pandora.model.wallet.WalletDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetWalletFundsResponse {
    boolean getWalletFunds;
    WalletDetails walletDetails;
}
