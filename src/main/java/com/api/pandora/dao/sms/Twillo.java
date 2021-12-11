package com.api.pandora.dao.sms;

import com.api.pandora.config.TwilloConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Twillo implements SMSVendor {

    @Autowired
    private TwilloConfig twilloConfig;

    @Override
    public String send(String contactNumber, String messageString) {
        try {
            Twilio.init(twilloConfig.getAccountSID(), twilloConfig.getAuthToken());
            Message message = Message.creator(new PhoneNumber(contactNumber),
                    new PhoneNumber(twilloConfig.getServerContactNumber()),
                    messageString).create();
            return message.getSid();
        } catch(Exception e) {
            return null;
        }
    }
}
