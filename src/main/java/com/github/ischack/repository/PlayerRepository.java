package com.github.ischack.repository;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.github.ischack.constants.Dynamo;
import com.github.ischack.model.Score;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mike on 6/28/14.
 */
public class PlayerRepository {

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
