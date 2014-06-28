package com.github.ischack.constants;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Created by mike on 6/27/14.
 */
public abstract class Dynamo {

    public static AmazonDynamoDBClient client;
    public static AWSCredentials credentials;
    static {
        credentials = new BasicAWSCredentials("AKIAJENOQGIWYWRTGMKA", "dc9QPXNE4cyeI8rfOAwqlTGUwoLSQOwdH+P1gGBp");
        client = new AmazonDynamoDBClient(credentials);
    }


    /** LOGIN */

    public static final String TABLE_ACCOUNTS = "isc-hack-accounts";

    public static final String USER_ID = "userId";
    public static final String USER_EMAIL = "userEmail";
    public static final String USER_PASSWORD = "userPassword";
    public static final String USER_TYPE = "userType";


    /** EVENTS */

    public static final String TABLE_EVENTS = "isc-hack-events";

    public static final String EVENT_ID = "eventId";
    public static final String EVENT_NAME = "name";
    public static final String EVENT_ORG = "organization";
    public static final String EVENT_PICTURE = "eventPic";
    public static final String EVENT_START = "startTime";
    public static final String EVENT_END = "endTime";
    public static final String EVENT_GAMES = "eventGames";


    /** GAMES */

    public static final String TABLE_GAMES = "isc-hack-games";

    public static final String GAME_ID = "gameId";
    public static final String GAME_NAME = "gameName";
    public static final String GAME_PICTURE = "gamePic";
    public static final String GAME_RULES = "gameRules";
    public static final String GAME_SCORETYPE = "scoreType";


    /** PLAYERS */

    public static final String TABLE_PLAYERS = "isc-hack-players";

    public static final String PLAYER_ID = "playerId";
    public static final String PLAYER_SCORES = "playerScores";


}
