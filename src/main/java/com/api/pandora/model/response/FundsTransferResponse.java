package com.api.pandora.model.response;

import com.api.pandora.model.wallet.WalletDetails;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FundsTransferResponse {
    private boolean fundsTransferred;
    private String payorMobileNumber;
    private String payeeMobileNumber;
    private String transactionId;
    private WalletDetails walletDetails;
}
