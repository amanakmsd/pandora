package com.api.pandora.dao.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class Twillo implements SMSVendor {
    public static final String ACCOUNT_SID = "AC6e52ed778ec5e284b457c89cf462766c";
    public static final String AUTH_TOKEN = "70b9a685c8e01e4b3b5135fb1c35a26a";
    public static final String SERVER_CONTACT_NO = "+13192572092";

    @Override
    public String send(String contactNumber, String messageString) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(new PhoneNumber(contactNumber),
                    new PhoneNumber(SERVER_CONTACT_NO),
                    messageString).create();
            return message.getSid();
        } catch(Exception e) {
            return null;
        }
    }
}
