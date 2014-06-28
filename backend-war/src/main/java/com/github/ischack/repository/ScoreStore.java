package com.github.ischack.repository;

import com.github.ischack.constants.Kind;
import com.github.ischack.model.Game;
import com.github.ischack.model.Score;
import com.google.appengine.api.datastore.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mike on 6/28/14.
 */
public class ScoreStore {

    public static Score getById(String id) {

        Query.Filter filter = new Query.FilterPredicate(
                Score.ID, Query.FilterOperator.EQUAL, id
        );

        Query query = new Query(Kind.SCORE).setFilter(filter);
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);

        Entity entity = pq.asSingleEntity();

        if (entity == null) {
            return null;
        }

        Score score = Score.fromEntity(entity);

        return score;

    }

    public static List<Score> getByGameId(String gameId) {

        Query.Filter filter = new Query.FilterPredicate(
                Score.GAMEID, Query.FilterOperator.EQUAL, gameId
        );

        Query query = new Query(Kind.SCORE).setFilter(filter);
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);

        List<Score> scores = new LinkedList<Score>();

        for (Entity e : pq.asIterable()) {
            scores.add(Score.fromEntity(e));
        }

        sort(scores);
        return scores;

    }

    public static List<Score> getByPlayerId(String playerId) {

        Query.Filter filter = new Query.FilterPredicate(
                Score.PLAYERID, Query.FilterOperator.EQUAL, playerId
        );

        Query query = new Query(Kind.SCORE).setFilter(filter);
        PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(query);

        List<Score> scores = new LinkedList<Score>();

        for (Entity e : pq.asIterable()) {
            scores.add(Score.fromEntity(e));
        }

        return scores;


    }

    public static void create(Score score) {

        String id = UUID.randomUUID().toString();
        Key key = KeyFactory.createKey(Kind.SCORE, id);
        score.setId(id);

        Entity gameEntity = new Entity(Kind.SCORE);
        score.buildEntity(gameEntity);

        DatastoreService service = DatastoreServiceFactory.getDatastoreService();
        service.put(gameEntity);

    }

    private static void sort(List<Score> scores) {
        scores.sort(new Comparator<Score>() {
            public int compare(Score o1, Score o2) {
                int i1, i2;
                try {
                    i1 = Integer.parseInt(o1.getScore());
                    i2 = Integer.parseInt(o2.getScore());
                } catch (NumberFormatException e) {
                    return 0;
                }
                return i2 - i1;
            }
        });
    }

}
