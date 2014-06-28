package com.github.ischack.repository;

import com.github.ischack.constants.Orchestrate;
import com.github.ischack.model.Game;
import com.github.ischack.model.Player;
import com.github.ischack.model.Score;

/**
 * Created by mike on 6/28/14.
 */
public class PlayerStore {

    public static void putScore(Score s) {

        // Update the player object
        Player p = Orchestrate.client.kv(Orchestrate.C_PLAYERS, s.getPlayerId())
                .get(Player.class).get().getValue();
        p.getScores().put(s.getGameId(), s.getScore());
        Orchestrate.client.kv(Orchestrate.C_PLAYERS, s.getPlayerId())
                .put(p).get();

        // Update the game object
        Game g = Orchestrate.client.kv(Orchestrate.C_GAMES, s.getGameId())
                .get(Game.class).get().getValue();
        g.getScores().put(s.getPlayerId(), s.getScore());
        Orchestrate.client.kv(Orchestrate.C_PLAYERS, s.getGameId())
                .put(g).get();

    }

}
