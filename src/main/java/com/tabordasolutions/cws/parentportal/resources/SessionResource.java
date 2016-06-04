package com.tabordasolutions.cws.parentportal.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.auth.SessionForm;
import com.tabordasolutions.cws.parentportal.services.SessionService;

@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionResource {

    private SessionService sessionService;

    public SessionResource(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @POST
    public Session login( SessionForm sessionParams){
        return sessionService.login(sessionParams.getEmail(), sessionParams.getPassword());
    }
}
