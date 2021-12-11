package com.api.pandora.dao.core;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.*;
import com.api.pandora.config.AWSConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DynamoDBCore {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    DynamoDB dynamoDBClient;

    public DynamoDBCore(AWSConfig awsConfig) {
         AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.AP_SOUTH_1)
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsConfig.getAccesskey(),
                                awsConfig.getSecretAccessKey())))
                .build();
         this.dynamoDBClient = new DynamoDB(amazonDynamoDB);

    }

    public boolean putItem(String tableName, String key, String value, Map<String, Object> attributeMap) {
        try {
            Table table = dynamoDBClient.getTable(tableName);
            table.waitForActive();
            Item item = prepareItem(key, value, attributeMap);
            long currentEpoch = System.currentTimeMillis();
            item.withNumber("CREATED_AT", currentEpoch);
            PutItemOutcome putItemOutcome = table.putItem(item);
            return putItemOutcome.getPutItemResult().getSdkHttpMetadata().getHttpStatusCode() == HttpStatus.SC_OK;
        } catch (Exception e) {
            return false;
        }
    }

    public Item getItem(String tableName, String key, String value)  {
        try {
            Table table = dynamoDBClient.getTable(tableName);
            table.waitForActive();
            PrimaryKey pk = new PrimaryKey(key, value);
            return table.getItem(pk);
        } catch (Exception e) {
            return null;
        }
    }

    private Item prepareItem(String key, String value, Map<String, Object> attributeMap) throws JsonProcessingException {
        Item item = new Item().withPrimaryKey(key, value);
        for (Map.Entry<String, Object> attribute: attributeMap.entrySet()) {
            String attributeName = attribute.getKey();
            Object attributeValue = attribute.getValue();
            if(attributeValue instanceof String) {
                item.withString(attributeName,(String) attributeValue);
            } else if(attributeValue instanceof Number) {
                item.withNumber(attributeName, (Number) attributeValue);
            } else if(attributeValue instanceof Boolean) {
                item.withBoolean(attributeName, (Boolean) attributeValue);
            } else if(attributeValue != null) {
                item.withJSON(attributeName, MAPPER.writeValueAsString(attributeValue));
            }
        }
        return item;
    }




}
