package com.github.ischack.controller;

import com.github.ischack.model.Event;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("event")
public class EventController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEvent() {
        Event event = new Event();
        event.setAdminId("asdf");
        return event;
    }

}
