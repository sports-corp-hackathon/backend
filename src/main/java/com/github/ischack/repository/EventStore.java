package com.github.ischack.repository;

import com.github.ischack.constants.Orchestrate;
import com.github.ischack.model.Event;
import io.orchestrate.client.KvObject;
import io.orchestrate.client.Result;
import io.orchestrate.client.SearchResults;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mike on 6/28/14.
 */
public class EventStore {

    public static List<Event> getAllEvents() {

        SearchResults<Event> results =
                Orchestrate.client.searchCollection(Orchestrate.C_EVENTS).get(Event.class, "*").get();

        List<Event> events = new LinkedList<>();
        for (Result<Event> result : results) {
            events.add(result.getKvObject().getValue());
        }

        return events;

    }

    public static Event getEvent(String eventId) {
        KvObject<Event> result = Orchestrate.client.kv(Orchestrate.C_EVENTS, eventId).get(Event.class).get();
        return result.getValue();
    }

    public static void createEvent(Event event) {
        String id = UUID.randomUUID().toString();
        event.setId(id);
        Orchestrate.client.kv(Orchestrate.C_EVENTS, id).put(event).get();
    }


}
