package com.api.pandora.dao.otp;


import com.api.pandora.model.otp.OTPDBResponse;

public interface OTPDAO {

    boolean saveOTP(String otp, String userContactNumber);
    OTPDBResponse getOTP(String userContactNumber);
}
