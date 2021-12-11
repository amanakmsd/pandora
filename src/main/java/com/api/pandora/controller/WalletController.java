package com.api.pandora.controller;

import com.api.pandora.model.request.AddWalletFundsRequest;
import com.api.pandora.model.request.FundsTransferRequest;
import com.api.pandora.model.request.GetWalletFundsRequest;
import com.api.pandora.model.response.AddWalletFundsResponse;
import com.api.pandora.model.response.FundsTransferResponse;
import com.api.pandora.model.response.GetWalletFundsResponse;
import com.api.pandora.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    /**
     * This API to be called after Payment integ is done
     */
    @PostMapping(
            value = "/wallet/add_funds",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AddWalletFundsResponse> addFunds(@RequestBody AddWalletFundsRequest addWalletFundsRequest) {
        AddWalletFundsResponse addWalletFundsResponse = walletService.addFundsInWallet(addWalletFundsRequest);
        return ResponseEntity
                .created(URI.create(String.format("%s", addWalletFundsResponse.isFundsAdded())))
                .body(addWalletFundsResponse);
    }
    @PostMapping(
            value = "/wallet/transfer_funds",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<FundsTransferResponse> transferFunds(@RequestBody FundsTransferRequest fundsTransferRequest) {
        FundsTransferResponse fundsTransferResponse = walletService.transferFunds(fundsTransferRequest);
        return ResponseEntity
                .created(URI.create(String.format("%s", fundsTransferResponse.isFundsTransferred())))
                .body(fundsTransferResponse);
    }
    @PostMapping(
            value = "/wallet/get_funds",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<GetWalletFundsResponse> getFunds(@RequestBody GetWalletFundsRequest getWalletFundsRequest) {
        GetWalletFundsResponse getWalletFundsResponse = walletService.getWalletFunds(getWalletFundsRequest);
        return ResponseEntity
                .created(URI.create(String.format("%s", getWalletFundsResponse.isGetWalletFunds())))
                .body(getWalletFundsResponse);
    }


}
