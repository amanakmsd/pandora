package com.api.pandora.dao.user;

import com.api.pandora.dao.core.DynamoDBCore;
import com.api.pandora.model.user.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class userDAOImpl implements UserDAO {

    @Autowired
    private DynamoDBCore dynamoDBCore;

    @Override
    public boolean addUser(UserDetails userDetails) {
        String tableName = "P_USER";
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("FNAME", userDetails.getFirstName());
        userMap.put("LNAME", userDetails.getLastName());
        userMap.put("USER_CONTACT_NUMBER", userDetails.getContactNumber());
        userMap.put("ADDRESS", userDetails.getAddress());
        userMap.put("IS_OTP_VERIFIED", false);
        return dynamoDBCore.putItem(tableName, "USER_ID", userDetails.getUserID(), userMap);
    }
}
