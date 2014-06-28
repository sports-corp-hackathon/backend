package com.github.ischack.controller;

import com.github.ischack.model.Score;
import com.github.ischack.repository.ScoreStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by mike on 6/28/14.
 */
@Path("/score")
public class ScoreController {

    @GET @Path("/{scoreId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getScore(
            @PathParam("scoreId") final String scoreId
    ) {
        Score s = ScoreStore.getById(scoreId);
        if (s == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(s).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createScore(String data) {
        Score s = null;
        try {
            s = Score.fromJson(data);
        } catch (IOException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ScoreStore.create(s);

        return Response.ok().entity(s).build();
    }

}
