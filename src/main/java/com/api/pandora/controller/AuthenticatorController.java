package com.api.pandora.controller;

import com.api.pandora.model.request.OTPRequest;
import com.api.pandora.model.request.OTPVerificationRequest;
import com.api.pandora.model.response.OTPResponse;
import com.api.pandora.model.response.OTPVerificationResponse;
import com.api.pandora.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class AuthenticatorController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(
            value = "/otp/send",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<OTPResponse> sendOTP(@RequestBody OTPRequest otpRequest) {
        OTPResponse otpResponse = authenticationService.sendOTPTaskTOSQS(otpRequest);
        return ResponseEntity
                .created(URI.create(String.format("%s", otpResponse.getMessageID())))
                .body(otpResponse);
    }

    @PostMapping(
            value = "/otp/verification",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<OTPVerificationResponse> verifyOTP(@RequestBody OTPVerificationRequest otpVerificationRequest) {
        OTPVerificationResponse otpVerificationResponse = authenticationService.verifyOTP(otpVerificationRequest);
        return ResponseEntity
                .created(URI.create(String.format("%s", otpVerificationResponse.isVerified())))
                .body(otpVerificationResponse);
    }
}
