package com.api.pandora.dao.sms;

public interface SMSVendor {
    public String send(String contactNumber, String message);
}
