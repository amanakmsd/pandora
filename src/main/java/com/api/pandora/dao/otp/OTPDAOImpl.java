package com.api.pandora.dao.otp;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.api.pandora.config.AWSConfig;
import com.api.pandora.dao.core.DynamoDBCore;
import com.api.pandora.model.otp.OTPDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OTPDAOImpl implements OTPDAO {

    @Autowired
    private DynamoDBCore dynamoDBCore;

    @Autowired
    private AWSConfig awsConfig;

    private static final String USER_CONTACT_NUMBER = "USER_CONTACT_NUMBER";
    private static final String OTP = "OTP";
    private static final String CREATED_AT = "CREATED_AT";

    @Override
    public boolean saveOTP(String otp, String userContactNumber) {
        Map<String, Object> otpMap = new HashMap<>();
        otpMap.put(OTP, otp);
        return dynamoDBCore.putItem(awsConfig.getOtpTableName(), USER_CONTACT_NUMBER, userContactNumber, otpMap);
    }

    @Override
    public OTPDBResponse getOTP(String userContactNumber) {
        try {
            Item item = dynamoDBCore.getItem(awsConfig.getOtpTableName(), USER_CONTACT_NUMBER, userContactNumber);
            if (item != null && item.hasAttribute(OTP) && item.hasAttribute(CREATED_AT))
                return new OTPDBResponse(item.getString(OTP), item.getLong(CREATED_AT));
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
