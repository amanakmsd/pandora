package com.api.pandora.util;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class CommonUtil {


    public String generateOTP() {
        return String.format("%04d", new Random().nextInt(10000));
    }

    public long timeDiffInMinutes(long time1, long time2) {
        return TimeUnit.MILLISECONDS.toMinutes(time2 - time1);
    }

    public String getOTPMessage(String otp) {
        return "OTP recieved from Pandora : "+otp + "\nExpires in : "+ Constants.OTP_EXP + " minutes.";
    }
}
