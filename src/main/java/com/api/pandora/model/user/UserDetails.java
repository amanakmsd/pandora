package com.api.pandora.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    private String userID;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private Address address;
    private boolean isOTPVerified;

}
