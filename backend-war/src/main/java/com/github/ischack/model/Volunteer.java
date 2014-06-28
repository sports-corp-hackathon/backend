package com.github.ischack.model;

import com.google.appengine.api.datastore.Entity;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by mike on 6/28/14.
 */
public class Volunteer {

    private String id;      public static String ID = "id";
    private String eventId; public static String EVENTID = "eventId";
    private String email;   public static String EMAIL = "email";
    private String name;    public static String NAME = "name";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void buildEntity(Entity e) {
        e.setProperty(ID, id);
        e.setProperty(EVENTID, eventId);
        e.setProperty(EMAIL, email);
        e.setProperty(NAME, name);
    }

    public static Volunteer fromEntity(Entity e) {
        Volunteer v = new Volunteer();
        v.setId((String) e.getProperty(ID));
        v.setEventId((String) e.getProperty(EVENTID));
        v.setEmail((String) e.getProperty(EMAIL));
        v.setName((String) e.getProperty(NAME));
        return v;
    }

    public static Volunteer fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Volunteer.class);
    }

}
