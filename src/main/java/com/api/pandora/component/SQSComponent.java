package com.api.pandora.component;

import com.api.pandora.model.request.OTPRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class SQSComponent {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private OTPComponent otpComponent;

    @JmsListener(destination = "OTP")
    public void receiveOTPMessage(TextMessage message) throws JMSException, JsonProcessingException {
       System.out.println("Received message "+ message.getText());
       OTPRequest otpRequest = MAPPER.readValue(message.getText(), OTPRequest.class);
       otpComponent.generateAndSendOTP(otpRequest);
       message.acknowledge();
    }


    public void sendToSQS(String destination, Object message) {
        jmsTemplate.convertAndSend(destination, message);
    }

}
