package com.github.ischack.model;

import java.util.Map;

/**
 * Created by mike on 6/28/14.
 */
public class Player {

    private String id;
    private Map<String, String> scores;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getScores() {
        return scores;
    }

    public void setScores(Map<String, String> scores) {
        this.scores = scores;
    }

}
