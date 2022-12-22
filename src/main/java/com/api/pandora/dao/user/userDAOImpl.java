package com.api.pandora.dao.user;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.api.pandora.dao.core.DynamoDBCore;
import com.api.pandora.model.user.Address;
import com.api.pandora.model.user.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
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

    @Override
    public UserDetails getUser(String contactNumber) {
        QuerySpec querySpec = new QuerySpec();
        querySpec.withKeyConditionExpression("USER_CONTACT_NUMBER = :contact_number")
                .withValueMap(new ValueMap().withString(":contact_number", contactNumber));
        ItemCollection<QueryOutcome> queryOutcomeItemCollection = dynamoDBCore
                .getDynamoDBItemBasedOnIndex("P_USER", "USER_CONTACT_NUMBER-index", querySpec);
        Iterator<Item> iterator = queryOutcomeItemCollection.iterator();
        Item userItem = iterator.next();
        Map addressMap = (Map) userItem.get("ADDRESS");
        return new UserDetails(userItem.getString("USER_ID"),
                userItem.getString("FNAME"), userItem.getString("LNAME"),
                userItem.getString("USER_CONTACT_NUMBER"),
                new Address((String) addressMap.get("localAddress"), (String) addressMap.get("city"), (BigDecimal) addressMap.get("pinCode")),
                userItem.getBoolean("IS_OTP_VERIFIED"));
    }
}
