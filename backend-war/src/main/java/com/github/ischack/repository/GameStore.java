package com.github.ischack.repository;

import com.github.ischack.constants.Kind;
import com.github.ischack.model.Event;
import com.github.ischack.model.Game;
import com.google.appengine.api.datastore.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mike on 6/28/14.
 */
public class GameStore {

    public static Game getGameById(String id) {
        Query.Filter filter = new Query.FilterPredicate(
                Game.ID, Query.FilterOperator.EQUAL, id
        );

        Query query = new Query(Kind.GAME).setFilter(filter);
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);

        Entity entity = pq.asSingleEntity();

        if (entity == null) {
            return null;
        }

        Game game = Game.fromEntity(entity);

        return game;


    }

    public static List<Game> getAllGames(String eventId) {
        Query.Filter eIdFilter = new Query.FilterPredicate(
                Game.EVENTID, Query.FilterOperator.EQUAL, eventId
        );

        Query query = new Query(Kind.GAME).setFilter(eIdFilter);
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);

        List<Game> list = new LinkedList<Game>();
        for (Entity entity : pq.asIterable()) {
            list.add(Game.fromEntity(entity));
        }

        if (list.size() == 0) {
            return null;
        }

        return list;
    }

    public static void createGame(Game g) {

        String id = UUID.randomUUID().toString();
        Key key = KeyFactory.createKey(Kind.GAME, id);
        g.setId(id);

        Entity gameEntity = new Entity(Kind.GAME);
        g.buildEntity(gameEntity);

        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        service.put(gameEntity);

    }

}
