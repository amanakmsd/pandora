package com.api.pandora.dao.wallet;


import com.amazonaws.services.dynamodbv2.document.Item;
import com.api.pandora.dao.core.DynamoDBCore;
import com.api.pandora.model.request.AddWalletFundsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WalletDAOImpl implements  WalletDAO {

    @Autowired
    private DynamoDBCore dynamoDBCore;


    @Override
    public boolean setFunds(AddWalletFundsRequest addWalletFundsRequest) {
        String tableName = "E_WALLET";
        Map<String, Object> walletMap = new HashMap<>();
        walletMap.put("USER_CONTACT_NUMBER", addWalletFundsRequest.getContactNumber());
        walletMap.put("WALLET_FUNDS", addWalletFundsRequest.getAmount());
        return dynamoDBCore.putItem(tableName, "USER_ID", addWalletFundsRequest.getUserID(), walletMap);
    }

    @Override
    public int getFunds(String  userId) {
        try {
            String tableName = "E_WALLET";
            Item item = dynamoDBCore.getItem(tableName, "USER_ID", userId);
            if (item.hasAttribute("WALLET_FUNDS")) {
                return item.getNumber("WALLET_FUNDS").toBigInteger().intValue();
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
