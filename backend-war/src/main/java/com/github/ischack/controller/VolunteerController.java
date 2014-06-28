package com.github.ischack.controller;

import com.github.ischack.constants.Header;
import com.github.ischack.model.Volunteer;
import com.github.ischack.repository.VolunteerStore;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by mike on 6/28/14.
 */
@Path("/volunteer")
public class VolunteerController {

    @GET @Path("/{volunteerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVolunteer(
            @PathParam("volunteerId") final String volunteerId
    ) {
        Volunteer v = VolunteerStore.get(volunteerId);
        if (v == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok()
                .entity(v)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createVolunteer(String data) {
        Volunteer v = null;
        try {
            v = Volunteer.fromJson(data);
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        VolunteerStore.create(v);

        return Response.ok()
                .entity(v)
                .build();

    }

}
