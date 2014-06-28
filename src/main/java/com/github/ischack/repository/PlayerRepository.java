package com.github.ischack.repository;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.github.ischack.constants.Dynamo;
import com.github.ischack.model.Score;

import java.util.*;

/**
 * Created by mike on 6/28/14.
 */
public class PlayerRepository {

    public static List<Score> getScoresByGame(String gameId) {

        ScanRequest request = new ScanRequest()
                .withTableName(Dynamo.TABLE_PLAYERS)
                .withAttributesToGet(Dynamo.PLAYER_ID, gameId);

        ScanResult results = Dynamo.client.scan(request);

        List<Score> scores = new LinkedList<>();

        for (Map<String, AttributeValue> m : results.getItems()) {
            Score s = new Score();
            s.setPlayerId(m.get(Dynamo.PLAYER_ID).getS());
            s.setScore(m.get(gameId).getS());
            scores.add(s);
        }

        scores.sort(new Comparator<Score>() {
            public int compare(Score o1, Score o2) {
                int i1, i2;
                try {
                    i1 = Integer.parseInt(o1.getScore());
                    i2 = Integer.parseInt(o2.getScore());
                } catch (NumberFormatException e) {
                    return 0;
                }
                return i1 - i2;
            }
        });

        return scores;

    }

    public static void putScore(Score s, String gameId) {

        Map<String, AttributeValue> map = new HashMap<>();
        map.put(Dynamo.PLAYER_ID, new AttributeValue().withS(s.getPlayerId()));
        map.put(gameId, new AttributeValue().withS(s.getScore()));

        PutItemRequest request = new PutItemRequest()
                .withTableName(Dynamo.TABLE_PLAYERS)
                .withItem(map);

        Dynamo.client.putItem(request);

    }

}
