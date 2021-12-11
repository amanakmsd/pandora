package com.api.pandora.dao.sms;

import com.api.pandora.vendor.provider.VendorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SMS {

    @Autowired
    private VendorProvider vendorProvider;

    public String send(String contactNumber, String message) {
        String messageId = null;
        String vendor = vendorProvider.getVendor("sms");
        if(vendor.equals("Twillo")) {
            SMSVendor smsVendor = new Twillo();
            messageId =  smsVendor.send(contactNumber, message);
        }
        if(messageId == null) {
            vendorProvider.reportError("sms", vendor);
            return send(contactNumber, message);
        }
        return messageId;
    }


}
