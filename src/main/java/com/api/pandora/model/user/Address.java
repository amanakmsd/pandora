package com.api.pandora.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String localAddress;
    private String city;
    private BigDecimal pinCode;

}
