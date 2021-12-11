package com.api.pandora.service;

import com.api.pandora.component.OTPComponent;
import com.api.pandora.dao.user.UserDAO;
import com.api.pandora.model.request.OnboardUserRequest;
import com.api.pandora.model.response.OnboardUserResponse;
import com.api.pandora.model.user.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;


    public OnboardUserResponse addUser(OnboardUserRequest onboardUserRequest) {

        if(!onboardUserRequest.isNewUser()) {
            return new OnboardUserResponse(false, null);
        }

        String userID = onboardUserRequest.getUserDetails().getContactNumber();
        UserDetails userDetails = onboardUserRequest.getUserDetails();
        userDetails.setUserID(userID);
        boolean isUserAdded = userDAO.addUser(userDetails);
        return new OnboardUserResponse(isUserAdded, userDetails);
    }

}
