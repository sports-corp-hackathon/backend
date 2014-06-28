package com.github.ischack.repository;

import com.github.ischack.constants.Kind;
import com.github.ischack.model.Game;
import com.github.ischack.model.Volunteer;
import com.google.appengine.api.datastore.*;

import java.util.UUID;

/**
 * Created by mike on 6/28/14.
 */
public class VolunteerStore {

    public static Volunteer get(String id) {

        Query.Filter filter = new Query.FilterPredicate(
                Volunteer.ID, Query.FilterOperator.EQUAL, id
        );

        Query query = new Query(Kind.VOLUNTEER).setFilter(filter);
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);

        Entity entity = pq.asSingleEntity();

        if (entity == null) {
            return null;
        }

        Volunteer v = Volunteer.fromEntity(entity);

        return v;
    }

    public static void create(Volunteer v) {

        String id = UUID.randomUUID().toString();
        Key key = KeyFactory.createKey(Kind.VOLUNTEER, id);
        v.setId(id);

        Entity vEntity = new Entity(Kind.VOLUNTEER);
        v.buildEntity(vEntity);

        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        service.put(vEntity);

    }

}
