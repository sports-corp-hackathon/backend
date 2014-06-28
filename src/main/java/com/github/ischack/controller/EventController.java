package com.github.ischack.controller;

import com.github.ischack.constants.Dynamo;
import com.github.ischack.model.Event;
import com.github.ischack.model.Game;
import com.github.ischack.repository.EventRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/event")
public class EventController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEvents() {
        return EventRepository.getAllEvents();
    }

    @GET @Path("/{" + Dynamo.EVENT_ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEvent(
            @PathParam(Dynamo.EVENT_ID) String eventId
    ) {
        return EventRepository.getEvent(eventId);
    }

    @GET @Path("/{" + Dynamo.EVENT_ID + "}/games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getAllGames(
            @PathParam(Dynamo.EVENT_ID) String eventId
    ) {
        return EventRepository.getAllGames(eventId);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String addEvent(String data) {

        try {
            Event e = Event.fromJson(data);
            EventRepository.putEvent(e);
        } catch (IOException e) {
            return e.getMessage();
        }

        return "success";

    }

    @DELETE @Path("/{" + Dynamo.EVENT_ID + "}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteEvent(
            @PathParam("eventId") String eventId
    ) {
        EventRepository.deleteEvent(eventId);
        return "success";
    }

}
