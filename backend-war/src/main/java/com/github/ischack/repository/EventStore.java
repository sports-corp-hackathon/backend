package com.github.ischack.repository;

import com.github.ischack.constants.Kind;
import com.github.ischack.model.Event;
import com.google.appengine.api.datastore.*;

import java.util.UUID;

/**
 * Created by mike on 6/28/14.
 */
public class EventStore {

    public static Event getEvent(String eventKey) {

        Key key = KeyFactory.stringToKey(eventKey);
        DatastoreService service = DatastoreServiceFactory.getDatastoreService();

        Event event = null;
        try {
            Entity e = service.get(key);
            event = Event.fromEntity(e);
        } catch (EntityNotFoundException e) {
            return null;
        }

        event.setId(eventKey);
        return event;
    }

    public static void createEvent(Event event) {

        Entity eventEntity = new Entity(Kind.EVENT);
        event.buildEntity(eventEntity);

        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        service.put(eventEntity);

        event.setId(KeyFactory.keyToString(eventEntity.getKey()));

    }


}
