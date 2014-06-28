package com.github.ischack.repository;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.github.ischack.constants.Dynamo;
import com.github.ischack.model.Account;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mike on 6/27/14.
 */
public class AccountRepository {

    private static Map<String, String> loginTokenMap;
    static {
        loginTokenMap = new ConcurrentHashMap<>();
    }

    public static Account createAccount(final Account a) {

        final String id = UUID.randomUUID().toString();
        a.setId(id);

        Map<String, AttributeValue> map = new HashMap<String, AttributeValue>() {{
            put(Dynamo.USER_ID, new AttributeValue().withS(id));
            put(Dynamo.USER_EMAIL, new AttributeValue().withS(a.getEmail()));
            put(Dynamo.USER_PASSWORD, new AttributeValue().withS(a.getPassword()));
        }};

        PutItemRequest request = new PutItemRequest(Dynamo.TABLE_ACCOUNTS, map);
        Dynamo.client.putItem(request);

        login(a);

        return a;

    }

    public static Account login(final Account a) {

        // Make sure the password they gave us is correct
        if (!verifyPassword(a)) {
            System.out.println("Login failed");
            return a;
        }

        // Generate a token and save it
        String token = UUID.randomUUID().toString() + UUID.randomUUID().toString();
        a.setLoginToken(token);
        loginTokenMap.put(a.getEmail(), token);

        System.out.println("login success");
        return a;

    }

    public static Account logout(final Account a) {
        loginTokenMap.remove(a.getId());
        a.setLoginToken("");
        return a;
    }

    private static boolean verifyPassword(final Account a) {

        Map<String, AttributeValue> map = new HashMap<String, AttributeValue>() {{
            put(Dynamo.USER_EMAIL, new AttributeValue().withS(a.getEmail()));
        }};

        GetItemRequest request = new GetItemRequest()
                .withTableName(Dynamo.TABLE_ACCOUNTS)
                .withKey(map);

        GetItemResult result = Dynamo.client.getItem(request);
        if (result == null) { return false; }

        Map<String, AttributeValue> item = result.getItem();
        if (item == null) { return false; }

        String password = item.get(Dynamo.USER_PASSWORD).getS();

        return password.equals(a.getPassword());

    }

}
