package com.github.ischack.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by mike on 6/28/14.
 */
@Path("/event")
public class EventController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "hello";
    }

}
