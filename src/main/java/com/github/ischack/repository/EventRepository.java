package com.github.ischack.repository;

import com.amazonaws.services.dynamodbv2.model.*;
import com.github.ischack.constants.Dynamo;
import com.github.ischack.model.Event;

import java.util.*;

/**
 * Created by mike on 6/27/14.
 */
public abstract class EventRepository {

    public static List<Event> getAllEvents() {
        ScanRequest request = new ScanRequest()
                .withTableName(Dynamo.TABLE_EVENTS);


        ScanResult result = Dynamo.client.scan(request);

        List<Event> results = new LinkedList<>();
        for (Map m : result.getItems()) {
            results.add(Event.fromMap(m));
        }

        return results;
    }

    public static Event getEvent(final String id) {

        Map<String, AttributeValue> map = new HashMap<String, AttributeValue>() {{
            put(Dynamo.EVENT_ID, new AttributeValue().withS(id));
        }};

        GetItemRequest request = new GetItemRequest()
                .withTableName(Dynamo.TABLE_EVENTS)
                .withKey(map);

        GetItemResult result = Dynamo.client.getItem(request);
        return Event.fromMap(result.getItem());

    }

    public static void putEvent(Event e) {

        Map<String, AttributeValue> map = new HashMap<>();

        map.put(Dynamo.EVENT_ID, new AttributeValue().withS(UUID.randomUUID().toString()));
        map.put(Dynamo.EVENT_NAME, new AttributeValue().withS(e.getName()));
        map.put(Dynamo.EVENT_ORG, new AttributeValue().withS(e.getOrganization()));
        map.put(Dynamo.EVENT_PICTURE, new AttributeValue().withS(e.getPicURL()));
        map.put(Dynamo.EVENT_START, new AttributeValue().withS(e.getStartTime()));
        map.put(Dynamo.EVENT_END, new AttributeValue().withS(e.getEndTime()));

        PutItemRequest request = new PutItemRequest()
                .withTableName(Dynamo.TABLE_EVENTS)
                .withItem(map);

        Dynamo.client.putItem(request);

    }

    public static void deleteEvent(String id) {

        Map map = new HashMap();
        map.put(Dynamo.EVENT_ID, new AttributeValue().withS(id));

        DeleteItemRequest request = new DeleteItemRequest()
                .withTableName(Dynamo.TABLE_EVENTS)
                .withKey(map);

        Dynamo.client.deleteItem(request);

    }
}
