package com.github.ischack.model;

import com.google.appengine.api.datastore.Entity;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by mike on 6/28/14.
 */
public class Player {

    private String id;      public static String ID = "id";
    private String eventId; public static String EVENTID = "eventId";
    private String email;   public static String EMAIL = "email";

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

    public void buildEntity(Entity e) {
        e.setProperty(ID, id);
        e.setProperty(EVENTID, eventId);
        e.setProperty(EMAIL, email);
    }

    public static Player fromEntity(Entity e) {
        Player p = new Player();
        p.setId((String) e.getProperty(ID));
        p.setEventId((String) e.getProperty(EVENTID));
        p.setEmail((String) e.getProperty(EMAIL));
        return p;
    }

    public static Player fromJson(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data, Player.class);
    }
}
