package com.github.ischack.controller;

import com.github.ischack.model.Game;
import com.github.ischack.repository.GameStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by mike on 6/28/14.
 */
@Path("/game")
public class GameController {

    @GET @Path("/{gameId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGame(
            @PathParam("gameId") final String gameId
    ) {
        Game g = GameStore.getGameById(gameId);
        if (g == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(g).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGame(String data) {

        Game g = null;
        try {
            g = Game.fromJson(data);
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        GameStore.createGame(g);

        return Response.ok().entity(g).build();

    }

}
