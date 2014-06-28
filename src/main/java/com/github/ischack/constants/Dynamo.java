package com.github.ischack.constants;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Created by mike on 6/27/14.
 */
public abstract class Dynamo {

    public static AmazonDynamoDBClient client;
    public static AWSCredentials credentials;
    static {
        credentials = new BasicAWSCredentials("AKIAJENOQGIWYWRTGMKA", "dc9QPXNE4cyeI8rfOAwqlTGUwoLSQOwdH+P1gGBp");
        client = new AmazonDynamoDBClient(credentials);
    }


    /** EVENTS */

    public static final String TABLE_EVENTS = "isc-hack-events";

    public static final String EVENT_ID = "eventId";
    public static final String EVENT_NAME = "name";
    public static final String EVENT_ORG = "organization";
    public static final String EVENT_PICTURE = "eventPic";
    public static final String EVENT_START = "startTime";
    public static final String EVENT_END = "endTime";


    /** LOGIN */

    public static final String TABLE_ACCOUNTS = "isc-hack-accounts";

    public static final String USER_ID = "userId";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_PASSWORD = "userPassword";

}
