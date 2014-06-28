package com.github.ischack.repository;

import com.github.ischack.constants.Orchestrate;
import com.github.ischack.model.Account;
import io.orchestrate.client.KvMetadata;
import io.orchestrate.client.KvObject;

import java.util.UUID;

/**
 * Created by mike on 6/28/14.
 */
public class AccountStore {

    public static void createAccount(Account account) {

        String id = UUID.randomUUID().toString();
        account.setId(id);

        Orchestrate.client.kv(Orchestrate.C_ACCOUNTS, id).put(account).get();

    }

    public static void login(Account account) {
        String sessionId = UUID.randomUUID().toString();
        Orchestrate.client.kv(Orchestrate.C_LOGINS, account.getId()).put(sessionId).get();
        account.setLoginToken(sessionId);
    }

}
