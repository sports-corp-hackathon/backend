package com.github.ischack.controller;

import com.github.ischack.constants.Dynamo;
import com.github.ischack.model.Game;
import com.github.ischack.model.Score;
import com.github.ischack.repository.GameStore;
import com.github.ischack.repository.PlayerRepository;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by mike on 6/27/14.
 */
@Path("/game")
public class GameController {

    @GET @Path("/{" + Dynamo.GAME_ID + "}/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Score> getScores(
            @PathParam(Dynamo.GAME_ID) final String gameId
    ) {
        return PlayerRepository.getScoresByGame(gameId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Game createGame(String data) {
        try {
            Game g = null;
            try {
                g = Game.fromJson(data);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            GameStore.createGame(g);
            return g;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @POST @Path("/{" + Dynamo.GAME_ID + "}/score")
    @Produces(MediaType.TEXT_PLAIN)
    public String scorePlayer(
            @PathParam(Dynamo.GAME_ID) final String gameId,
            String data
    ) {

        Score score = null;
        try {
            score = Score.fromJson(data);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }

        PlayerRepository.putScore(score, gameId);

        return "success";

    }

}

