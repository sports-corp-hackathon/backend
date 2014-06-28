package com.github.ischack.controller;

import com.github.ischack.model.Account;
import com.github.ischack.repository.AccountRepository;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by mike on 6/27/14.
 */
@Path("/account")
public class AccountController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Account createAccount(String data) {

        Account account = null;
        try {
            account = Account.fromJson(data);
        } catch (IOException e) {
            return null;
        }

        if (AccountRepository.createAccount(account) == null) {
            return null;
        }

        return account;

    }


    @POST @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Account login(String data) {

        Account attempt = null;
        try {
            attempt = Account.fromJson(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        AccountRepository.login(attempt);

        return attempt;

    }


}
