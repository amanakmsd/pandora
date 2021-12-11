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
public class AWSConfig {
    @Value("${aws.accesskey}")
    private String accesskey;
    @Value("${aws.secretAccessKey}")
    private String secretAccessKey;
    @Value("${otp.tableName}")
    private String otpTableName;
    @Value("${otp.queueName}")
    private String otpQueueName;
}
