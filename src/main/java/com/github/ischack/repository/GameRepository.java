package com.github.ischack.repository;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.github.ischack.constants.Dynamo;
import com.github.ischack.model.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by mike on 6/27/14.
 */
public class GameRepository {

    public static Game getGame(final String id) {

        Map<String, AttributeValue> map = new HashMap<>();
        map.put(Dynamo.GAME_ID, new AttributeValue().withS(id));

        GetItemRequest request = new GetItemRequest(Dynamo.TABLE_GAMES, map);
        GetItemResult result = Dynamo.client.getItem(request);

        Game g = Game.fromMap(result.getItem());
        return g;
    }

    public static void putGame(final Game g) {

        String id = UUID.randomUUID().toString();
        g.setId(id);

        Map<String, AttributeValue> map = new HashMap<String, AttributeValue>() {{
            put(Dynamo.GAME_ID, new AttributeValue().withS(g.getId()));
            put(Dynamo.EVENT_ID, new AttributeValue().withS(g.getEventId()));
            put(Dynamo.GAME_NAME, new AttributeValue().withS(g.getName()));
            put(Dynamo.GAME_PICTURE, new AttributeValue().withS(g.getGamePic()));
            put(Dynamo.GAME_RULES, new AttributeValue().withS(g.getRules()));
            put(Dynamo.GAME_SCORETYPE, new AttributeValue().withS(g.getScoreType()));
        }};

        PutItemRequest request = new PutItemRequest()
                .withTableName(Dynamo.TABLE_GAMES)
                .withItem(map);

        Dynamo.client.putItem(request);

        EventRepository.addGameToEvent(g);

    }




}
