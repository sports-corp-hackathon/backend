package com.github.ischack.controller;

import com.github.ischack.constants.Dynamo;
import com.github.ischack.model.Event;
import com.github.ischack.model.Game;
import com.github.ischack.repository.EventStore;
import com.github.ischack.repository.GameStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/event")
public class EventController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEvents() {
        return EventStore.getAllEvents();
    }

    @GET @Path("/{" + Dynamo.EVENT_ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEvent(
            @PathParam(Dynamo.EVENT_ID) String eventId
    ) {
        return EventStore.getEvent(eventId);
    }

    @GET @Path("/{" + Dynamo.EVENT_ID + "}/games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getAllGames(
            @PathParam(Dynamo.EVENT_ID) String eventId
    ) {
        return GameStore.getGamesByEvent(eventId);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String addEvent(String data) {

        try {
            Event e = Event.fromJson(data);
            EventStore.createEvent(e);
        } catch (IOException e) {
            return e.getMessage();
        }

        return "success";

    }

}
