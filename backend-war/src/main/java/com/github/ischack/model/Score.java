package com.github.ischack.model;


import com.github.ischack.repository.GameStore;
import com.github.ischack.repository.PlayerStore;
import com.google.appengine.api.datastore.Entity;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by mike on 6/28/14.
 */
public class Score {

    private String id;          public static String ID = "id";
    private String playerId;    public static String PLAYERID = "playerId";
    private Game game;
    private String gameId;      public static String GAMEID = "gameId";
    private String score;       public static String SCORE = "score";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void buildEntity(Entity e) {
        e.setProperty(ID, id);
        e.setProperty(PLAYERID, playerId);
        e.setProperty(GAMEID, gameId);
        e.setProperty(SCORE, score);
    }

    public static Score fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Score.class);
    }

    public static Score fromEntity(Entity e) {
        Score s = new Score();
        s.setId((String) e.getProperty(ID));
        s.setPlayerId((String) e.getProperty(PLAYERID));
        s.setGameId((String) e.getProperty(GAMEID));
        s.setGame(GameStore.getGameById(s.getGameId()));
        s.setScore((String) e.getProperty(SCORE));
        return s;
    }
}
