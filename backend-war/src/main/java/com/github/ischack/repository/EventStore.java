package com.github.ischack.repository;

import com.github.ischack.constants.Kind;
import com.github.ischack.model.Event;
import com.github.ischack.model.Game;
import com.google.appengine.api.datastore.*;

import java.util.UUID;

/**
 * Created by mike on 6/28/14.
 */
public class EventStore {

    public static Event getEvent(String eventKey) {

        Query.Filter eIdFilter = new Query.FilterPredicate(
                Event.ID, Query.FilterOperator.EQUAL, eventKey
        );

        Query query = new Query(Kind.EVENT).setFilter(eIdFilter);
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);
        Entity e = pq.asSingleEntity();
        Event event = Event.fromEntity(e);
        return event;
    }

    public static void createEvent(Event event) {

        String id = UUID.randomUUID().toString();
        Key key = KeyFactory.createKey(Kind.EVENT, id);
        event.setId(id);

        Entity eventEntity = new Entity(Kind.EVENT, key);
        event.buildEntity(eventEntity);

        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        service.put(eventEntity);

    }


}
