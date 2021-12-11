package com.api.pandora.model.user;

import lombok.Data;

@Data
public class Address {

    private String localAddress;
    private String city;
    private int pinCode;

}
