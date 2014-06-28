package com.github.ischack.repository;

import com.github.ischack.constants.Kind;
import com.github.ischack.model.Event;
import com.github.ischack.model.Game;
import com.google.appengine.api.datastore.*;

/**
 * Created by mike on 6/28/14.
 */
public class GameStore {

    public static Game getGameById(String id) {
        Key key = KeyFactory.stringToKey(id);
        DatastoreService service = DatastoreServiceFactory.getDatastoreService();

        Game game = null;
        try {
            Entity e = service.get(key);
            game = Game.fromEntity(e);
        } catch (EntityNotFoundException e) {
            return null;
        }

        game.setId(id);
        return game;
    }

    public static void createGame(Game g) {

        Entity gameEntity = new Entity(Kind.GAME);
        g.buildEntity(gameEntity);

        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        service.put(gameEntity);

        g.setId(KeyFactory.keyToString(gameEntity.getKey()));

    }

}
