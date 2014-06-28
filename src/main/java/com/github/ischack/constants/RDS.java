package com.github.ischack.constants;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.rds.AmazonRDSClient;

/**
 * Created by mike on 6/28/14.
 */
public class RDS {

    public static AmazonRDSClient client;
    public static BasicAWSCredentials credentials;
    static {
        credentials = new BasicAWSCredentials("AKIAJENOQGIWYWRTGMKA", "dc9QPXNE4cyeI8rfOAwqlTGUwoLSQOwdH+P1gGBp");
        client = new AmazonRDSClient(credentials);
    }

    public static final String DB_INSTANCE = "isc-hack";

    /** Accounts **/
    public static final String TABLE_ACCOUNTS = "accounts";

    public static final String USER_ID = "user_id";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_TYPE = "user_type";

}
