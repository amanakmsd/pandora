package com.api.pandora.component;

import com.api.pandora.dao.otp.OTPDAO;
import com.api.pandora.dao.sms.SMS;
import com.api.pandora.model.otp.OTPDBResponse;
import com.api.pandora.model.request.OTPRequest;
import com.api.pandora.model.response.OTPResponse;
import com.api.pandora.model.request.OTPVerificationRequest;
import com.api.pandora.model.response.OTPVerificationResponse;
import com.api.pandora.util.CommonUtil;
import com.api.pandora.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OTPComponent {

    @Autowired
    private OTPDAO otpdao;

    @Autowired
    private SMS sms;

    @Autowired
    private CommonUtil commonUtil;

    public OTPResponse generateAndSendOTP(OTPRequest otpRequest) {
        //Generate OTP
        String otp = commonUtil.generateOTP();

        //Save OTP in DB for later on verification.
        boolean val = otpdao.saveOTP(otp, otpRequest.getContactNumber());
        if(!val) return new OTPResponse(false, null);

        //Send OTP to customer.
        String messageID = sms.send(otpRequest.getContactNumber(), commonUtil.getOTPMessage(otp));
        if(messageID == null) return new OTPResponse(false, null);
        return new OTPResponse(true, messageID);
    }


    public OTPVerificationResponse verifyOTP(OTPVerificationRequest otpVerificationRequest) {
        try {
            //Read OTP from DB.
            OTPDBResponse otpDBResponse = otpdao.getOTP(otpVerificationRequest.getContactNumber());
            if (otpDBResponse == null ||
                    otpDBResponse.getOtp() == null ||
                    otpDBResponse.getCreatedAt() == null)
                return new OTPVerificationResponse(false, false);
            Long otpCreationTime = otpDBResponse.getCreatedAt();
            String otpExpected = otpDBResponse.getOtp();

            //Check if OTP is expired
            if (commonUtil.timeDiffInMinutes(otpCreationTime,
                    System.currentTimeMillis()) > Constants.OTP_EXP)
                return new OTPVerificationResponse(true, false);

            //Check OTP from DB and request
            return new OTPVerificationResponse(true, Objects.equals(otpVerificationRequest.getOtp(), otpExpected));
        } catch (Exception e) {
            return new OTPVerificationResponse(false, false);
        }
    }
}
