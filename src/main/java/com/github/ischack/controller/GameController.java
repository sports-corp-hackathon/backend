package com.github.ischack.controller;

import com.github.ischack.model.Game;
import com.github.ischack.repository.GameRepository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by mike on 6/27/14.
 */
@Path("/game")
public class GameController {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public void createGame(String data) {
        try {
            Game g = null;
            try {
                g = Game.fromJson(data);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            GameRepository.putGame(g);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

