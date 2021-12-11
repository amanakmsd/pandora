package com.api.pandora.dao.wallet;

import com.api.pandora.model.request.AddWalletFundsRequest;

public interface WalletDAO {

    boolean setFunds(AddWalletFundsRequest addWalletFundsRequest);

    int getFunds(String userId);
}
