package com.api.pandora.model.user;

import lombok.Data;

@Data
public class UserDetails {

    private String userID;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private Address address;
    private boolean isOTPVerified;

}
