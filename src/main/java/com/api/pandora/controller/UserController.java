package com.api.pandora.controller;

import com.api.pandora.model.request.OnboardUserRequest;
import com.api.pandora.model.response.OnboardUserResponse;
import com.api.pandora.model.user.UserDetails;
import com.api.pandora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            value = "/user/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<OnboardUserResponse> addUser(@RequestBody OnboardUserRequest onboardUserRequest) {
        OnboardUserResponse onboardUserResponse = userService.addUser(onboardUserRequest);
        return ResponseEntity
                .created(URI.create(String.format("%s", onboardUserResponse.isUserOnboarded())))
                .body(onboardUserResponse);
    }

    //TODO: Get user details
    @PostMapping(
            value = "/user/get",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserDetails> getUser(@RequestBody OnboardUserRequest onboardUserRequest) {
        UserDetails userDetails = userService.getUser("+918317030424");
        return ResponseEntity
                .created(URI.create(String.format("%s", userDetails.getUserID())))
                .body(userDetails);
    }


}
