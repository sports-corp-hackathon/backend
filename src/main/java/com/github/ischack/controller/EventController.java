package com.github.ischack.controller;

import argo.saj.InvalidSyntaxException;
import com.github.ischack.constants.Dynamo;
import com.github.ischack.model.Event;
import com.github.ischack.repository.EventRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/event")
public class EventController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEvents() {
        //return EventRepository.getAllEvents();
        return "SUP SON"; //asdf
    }

    @GET @Path("/{" + Dynamo.EVENT_ID + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEvent(
            @PathParam(Dynamo.EVENT_ID) String eventId
    ) {
        return EventRepository.getEvent(eventId);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String addEvent(String data) {

        try {
            Event e = Event.fromJsonFromPost(data);
            EventRepository.putEvent(e);
        } catch (InvalidSyntaxException e) {
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
