package com.github.ischack.model;

import argo.jdom.JdomParser;
import argo.jdom.JsonRootNode;
import argo.saj.InvalidSyntaxException;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.util.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ischack.constants.Dynamo;

import java.io.IOError;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Event {

    private String id;
    private String name;
    private String organization;
    private String eventPic;
    private String startTime;
    private String endTime;
    private List<String> games;

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

    public List<String> getGames() {
        return games;
    }

    public void setGames(List<String> games) {
        this.games = games;
    }

    public static Event fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Event.class);
    }

    public static Event fromMap(Map<String, AttributeValue> map) {
        Event e = new Event();

        AttributeValue v = map.get(Dynamo.EVENT_ID);
        if (v != null) { e.setId(v.getS()); }

        v = map.get(Dynamo.EVENT_NAME);
        if (v != null) { e.setName(v.getS()); }

        v = map.get(Dynamo.EVENT_ORG);
        if (v != null) { e.setOrganization(v.getS()); }

        v = map.get(Dynamo.EVENT_PICTURE);
        if (v != null) { e.setEventPic(v.getS()); }

        v = map.get(Dynamo.EVENT_START);
        if (v != null) { e.setStartTime(v.getS()); }

        v = map.get(Dynamo.EVENT_END);
        if (v != null) { e.setEndTime(v.getS()); }

        v = map.get(Dynamo.EVENT_GAMES);
        if (v != null) { e.setGames(v.getSS()); }

        return e;
    }

}
