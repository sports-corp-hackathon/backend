package com.github.ischack.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by mike on 6/28/14.
 */
public class Score {

    private String playerId;
    private String gameId;
    private String score;

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

    public static Score fromJson(String s) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(s, Score.class);
    }
}
