package com.github.ischack.controller;

import com.github.ischack.constants.Header;
import com.github.ischack.model.Event;
import com.github.ischack.model.Game;
import com.github.ischack.repository.EventStore;
import com.github.ischack.repository.GameStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by mike on 6/28/14.
 */
@Path("/event")
public class EventController {

    @GET @Path("/{eventId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEventById(
            @PathParam("eventId") final String eventId
    ) {

        Event event = EventStore.getEvent(eventId);
        if (event == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok()
                .entity(event)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEvent(String data) {

        Event event = null;

        try {
            event = Event.fromJson(data);
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        EventStore.createEvent(event);

        return Response.ok()
                .entity(event)
                .build();
    }

    @GET @Path("/{eventId}/games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getAllGames(
            @PathParam("eventId") final String eventId
    ) {
        List<Game> allGames = GameStore.getAllGames(eventId);
        return allGames;
    }

}
