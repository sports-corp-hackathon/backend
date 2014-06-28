package com.github.ischack.repository;

import com.github.ischack.constants.Orchestrate;
import com.github.ischack.model.Event;
import com.github.ischack.model.Game;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mike on 6/28/14.
 */
public class GameStore {

    public static List<Game> getGamesByEvent(String eventId) {
        Event e = EventStore.getEvent(eventId);
        List<Game> games = new LinkedList<>();
        for (String gid : e.getGames()) {
            games.add(GameStore.getGame(gid));
        }
        return games;
    }

    public static Game getGame(String gameId) {
        return Orchestrate.client.kv(Orchestrate.C_GAMES, gameId).get(Game.class).get().getValue();
    }

    public static void createGame(Game g) {
        String gid = UUID.randomUUID().toString();
        g.setId(gid);
        Orchestrate.client.kv(Orchestrate.C_GAMES, gid).put(g).get();
    }

}
