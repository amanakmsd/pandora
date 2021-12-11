package com.api.pandora.service;

import com.api.pandora.component.OTPComponent;
import com.api.pandora.component.SQSComponent;
import com.api.pandora.config.AWSConfig;
import com.api.pandora.model.request.OTPRequest;
import com.api.pandora.model.request.OTPVerificationRequest;
import com.api.pandora.model.response.OTPResponse;
import com.api.pandora.model.response.OTPVerificationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private OTPComponent otpComponent;

    @Autowired
    private SQSComponent sqsComponent;

    @Autowired
    private AWSConfig awsConfig;


    public OTPVerificationResponse verifyOTP(OTPVerificationRequest otpVerificationRequest) {
        return otpComponent.verifyOTP(otpVerificationRequest);
    }

    public OTPResponse sendOTP(OTPRequest otpRequest) {
        return otpComponent.generateAndSendOTP(otpRequest);
    }

    public OTPResponse sendOTPTaskTOSQS(OTPRequest otpRequest) {
        try {
            String message = MAPPER.writeValueAsString(otpRequest);
            sqsComponent.sendToSQS(awsConfig.getOtpQueueName(), message);
            return new OTPResponse(true, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new OTPResponse(false, null);
        }
    }

}
