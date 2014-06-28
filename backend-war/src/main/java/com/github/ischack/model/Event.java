package com.github.ischack.model;

import com.google.appengine.api.datastore.Entity;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class Event {

    private String id;              public static String ID = "id";
    private String name;            public static String NAME = "name";
    private String organization;    public static String ORG = "organization";
    private String eventPic;        public static String PIC = "eventPic";
    private String startTime;       public static String START = "startTime";
    private String endTime;         public static String END = "endTime";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEventPic() {
        return eventPic;
    }

    public void setEventPic(String eventPic) {
        this.eventPic = eventPic;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void buildEntity(Entity entity) {
        entity.setProperty(ID, id);
        entity.setProperty(NAME, name);
        entity.setProperty(ORG, organization);
        entity.setProperty(PIC, eventPic);
        entity.setProperty(START, startTime);
        entity.setProperty(END, endTime);
    }

    public static Event fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Event.class);
    }

    public static Event fromEntity(Entity entity) {
        Event event = new Event();
        event.setId((String) entity.getProperty(ID));
        event.setName((String) entity.getProperty(NAME));
        event.setOrganization((String) entity.getProperty(ORG));
        event.setEventPic((String) entity.getProperty(PIC));
        event.setStartTime((String) entity.getProperty(START));
        event.setEndTime((String) entity.getProperty(END));
        return event;
    }
}