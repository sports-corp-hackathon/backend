package com.github.ischack.model;

import com.google.appengine.api.datastore.Entity;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by mike on 6/28/14.
 */
public class Game {

    private String id;          public static String ID = "id";
    private String eventId;     public static String EVENTID = "eventId";
    private String name;        public static String NAME = "name";
    private String gamePic;     public static String PIC = "gamePic";
    private String rules;       public static String RULES = "rules";
    private String scoreType;   public static String SCORETYPE = "scoreType";

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

    public void buildEntity(Entity e) {
        e.setProperty(ID, id);
        e.setProperty(EVENTID, eventId);
        e.setProperty(NAME, name);
        e.setProperty(PIC, gamePic);
        e.setProperty(RULES, rules);
        e.setProperty(SCORETYPE, scoreType);
    }

    public static Game fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Game.class);
    }

    public static Game fromEntity(Entity e) {
        Game g = new Game();
        g.setId((String) e.getProperty(ID));
        g.setScoreType((String) e.getProperty(SCORETYPE));
        g.setEventId((String) e.getProperty(EVENTID));
        g.setRules((String) e.getProperty(RULES));
        g.setGamePic((String) e.getProperty(PIC));
        g.setName((String) e.getProperty(NAME));
        return g;
    }

}
