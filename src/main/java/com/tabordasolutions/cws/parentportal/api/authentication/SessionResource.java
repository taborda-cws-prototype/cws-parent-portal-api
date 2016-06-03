package com.tabordasolutions.cws.parentportal.api.authentication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionResource {
    private SessionService sessionService;

    public SessionResource(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @POST
    public Session login(@PathParam("email") String email, @PathParam("password") String password){

        boolean valid = sessionService.login(email, password);
        return new Session(valid, "");
    }

}
