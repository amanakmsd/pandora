package com.api.pandora.vendor.provider;

import org.springframework.stereotype.Component;

@Component
public class VendorProvider {

    public String getVendor(String mode) {
        return "Twillo";
    }

    public void reportError(String mode, String vendor) {
    }
}
