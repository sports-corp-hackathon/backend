package com.github.ischack.constants;

import io.orchestrate.client.Client;
import io.orchestrate.client.OrchestrateClient;

/**
 * Created by mike on 6/28/14.
 */
public class Orchestrate {

    public static Client client;
    static {
        client = new OrchestrateClient("314b8c7c-147e-4c60-b145-86b4a9224f76");
    }

    /** Accounts **/
    public static final String C_ACCOUNTS = "accounts";
    public static final String C_LOGINS = "logins";

    /** Events **/
    public static final String C_EVENTS = "events";

    /** Games **/
    public static final String C_GAMES = "games";

    /** Players */
    public static final String C_PLAYERS = "players";
}
