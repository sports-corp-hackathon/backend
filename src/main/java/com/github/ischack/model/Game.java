package com.github.ischack.model;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ischack.constants.Dynamo;

import java.io.IOException;
import java.util.Map;

/**
 * Created by mike on 6/27/14.
 */
public class Game {

    private String id;
    private String eventId;
    private String name;
    private String gamePic;
    private String rules;
    private String scoreType;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGamePic() {
        return gamePic;
    }

    public void setGamePic(String gamePic) {
        this.gamePic = gamePic;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public static Game fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Game.class);
    }

    public static Game fromMap(Map<String, AttributeValue> map) {
        Game g = new Game();

        AttributeValue v = map.get(Dynamo.GAME_ID);
        if (v != null) { g.setId(v.getS()); }

        v = map.get(Dynamo.EVENT_ID);
        if (v != null) { g.setEventId(v.getS()); }

        v = map.get(Dynamo.GAME_NAME);
        if (v != null) { g.setName(v.getS()); }

        v = map.get(Dynamo.GAME_PICTURE);
        if (v != null) { g.setGamePic(v.getS()); }

        v = map.get(Dynamo.GAME_RULES);
        if (v != null) { g.setRules(v.getS()); }

        v = map.get(Dynamo.GAME_SCORETYPE);
        if (v != null) { g.setScoreType(v.getS()); }

        return g;
    }

}
