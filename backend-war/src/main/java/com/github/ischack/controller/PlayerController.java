package com.github.ischack.controller;

import com.github.ischack.model.Player;
import com.github.ischack.model.Score;
import com.github.ischack.repository.PlayerStore;
import com.github.ischack.repository.ScoreStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by mike on 6/28/14.
 */
@Path("/player")
public class PlayerController {

    @GET @Path("/{playerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayer(
            @PathParam("playerId") final String playerId
    ) {
        Player p = PlayerStore.get(playerId);
        if (p == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(p).build();
    }

    @GET @Path("/{playerId}/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Score> getScores(
            @PathParam("playerId") final String playerId
    ) {
        return ScoreStore.getByPlayerId(playerId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPlayer(String data) {
        Player p = null;
        try {
            p = Player.fromJson(data);
        } catch (IOException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        PlayerStore.create(p);

        return Response.ok().entity(p).build();
    }



}
