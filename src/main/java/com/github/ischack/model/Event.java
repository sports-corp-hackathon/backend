package com.github.ischack.model;

import argo.jdom.JdomParser;
import argo.jdom.JsonRootNode;
import argo.saj.InvalidSyntaxException;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.util.json.JSONObject;
import com.github.ischack.constants.Dynamo;

import java.util.Map;

public class Event {

    private String id;
    private String name;
    private String organization;
    private String picURL;
    private String startTime;
    private String endTime;

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

    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
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


    public static Event fromJsonFromPost(String json) throws InvalidSyntaxException {
        JdomParser parser = new JdomParser();
        JsonRootNode node = parser.parse(json);

        Event e = new Event();
        e.setName(node.getStringValue("name"));
        e.setOrganization(node.getStringValue("organization"));
        e.setPicURL(node.getStringValue("picture"));
        e.setStartTime(node.getStringValue("startTime"));
        e.setEndTime(node.getStringValue("endTime"));

        return e;
    }

    public static Event fromMap(Map<String, AttributeValue> map) {
        Event e = new Event();
        e.setId(map.get(Dynamo.EVENT_ID).getS());
        e.setName(map.get(Dynamo.EVENT_NAME).getS());
        e.setOrganization(map.get(Dynamo.EVENT_ORG).getS());
        e.setPicURL(map.get(Dynamo.EVENT_PICTURE).getS());
        e.setStartTime(map.get(Dynamo.EVENT_START).getS());
        e.setEndTime(map.get(Dynamo.EVENT_END).getS());

        return e;
    }

}
