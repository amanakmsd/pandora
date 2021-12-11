package com.api.pandora.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.dev.properties")
@Getter
@Setter
public class TwilloConfig {
    @Value("${twillo.accountSID}")
    private String accountSID;
    @Value("${twillo.authToken}")
    private String authToken;
    @Value("${twillo.serverContactNumber}")
    private String serverContactNumber;
}
