package com.api.pandora.service;

import com.api.pandora.dao.wallet.WalletDAO;
import com.api.pandora.model.request.AddWalletFundsRequest;
import com.api.pandora.model.request.FundsTransferRequest;
import com.api.pandora.model.request.GetWalletFundsRequest;
import com.api.pandora.model.response.AddWalletFundsResponse;
import com.api.pandora.model.response.FundsTransferResponse;
import com.api.pandora.model.response.GetWalletFundsResponse;
import com.api.pandora.model.wallet.WalletDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletDAO walletDAO;

    public AddWalletFundsResponse addFundsInWallet(AddWalletFundsRequest addWalletFundsRequest) {
        try {
            int currentBalance = walletDAO.getFunds(addWalletFundsRequest.getUserID());
            int newBalance = currentBalance + addWalletFundsRequest.getAmount();
            addWalletFundsRequest.setAmount(newBalance);
            boolean fundsAdded = walletDAO.setFunds(addWalletFundsRequest);
            WalletDetails walletDetails = new WalletDetails(addWalletFundsRequest.getUserID(), addWalletFundsRequest.getContactNumber(), newBalance);
            return new AddWalletFundsResponse(fundsAdded, walletDetails);
        } catch (Exception e) {
            return new AddWalletFundsResponse(false, new WalletDetails(addWalletFundsRequest.getUserID(),
                    addWalletFundsRequest.getContactNumber(), 0));
        }
    }

    public FundsTransferResponse transferFunds(FundsTransferRequest fundsTransferRequest) {
        int currentBalance = walletDAO.getFunds(fundsTransferRequest.getUserId());
        int transferAmount = fundsTransferRequest.getTransferRequestAmount();
        if(currentBalance < transferAmount) {
            return new FundsTransferResponse(false, fundsTransferRequest.getPayerMobileNumber(),
                    fundsTransferRequest.getPayeeMobileNumber(), null,
                    new WalletDetails(fundsTransferRequest.getUserId(), fundsTransferRequest.getPayerMobileNumber(), currentBalance));
        }
        //TODO:- Make this transactional;
        walletDAO.setFunds(new AddWalletFundsRequest(fundsTransferRequest.getUserId(), fundsTransferRequest.getPayerMobileNumber(), currentBalance-transferAmount));
        //TODO:- Get UserId based on contact number , currently assuming both are same
        int payeeBalance = walletDAO.getFunds(fundsTransferRequest.getPayeeMobileNumber());
        walletDAO.setFunds(new AddWalletFundsRequest(fundsTransferRequest.getPayeeMobileNumber(), fundsTransferRequest.getPayeeMobileNumber(), payeeBalance+transferAmount));
        //TODO:-Save this transaction in transaction table
        return new FundsTransferResponse(true, fundsTransferRequest.getPayerMobileNumber(), fundsTransferRequest.getPayeeMobileNumber(), "ABCD",
                new WalletDetails(fundsTransferRequest.getUserId(), fundsTransferRequest.getPayerMobileNumber(), currentBalance - transferAmount));
    }

    public GetWalletFundsResponse getWalletFunds(GetWalletFundsRequest getWalletFundsRequest) {
        int currentBalance = walletDAO.getFunds(getWalletFundsRequest.getUserId());
        return new GetWalletFundsResponse(true, new WalletDetails(getWalletFundsRequest.getUserId(), getWalletFundsRequest.getUserId(), currentBalance));
    }

}
