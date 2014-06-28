package com.github.ischack.repository;

import com.github.ischack.constants.Kind;
import com.github.ischack.model.Event;
import com.github.ischack.model.Player;
import com.google.appengine.api.datastore.*;

/**
 * Created by mike on 6/28/14.
 */
public class PlayerStore {

    public static Player get(String id) {
        Query.Filter eIdFilter = new Query.FilterPredicate(
                Player.ID, Query.FilterOperator.EQUAL, id
        );

        Query query = new Query(Kind.PLAYER).setFilter(eIdFilter);
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);
        Entity e = pq.asSingleEntity();
        Player p = Player.fromEntity(e);
        return p;
    }

    public static void create(Player p) {

        Key key = KeyFactory.createKey(Kind.PLAYER, p.getId());

        Entity entity = new Entity(Kind.PLAYER, key);
        p.buildEntity(entity);

        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        service.put(entity);
    }

}
