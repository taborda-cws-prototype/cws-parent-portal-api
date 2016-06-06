package com.tabordasolutions.cws.parentportal.resources;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/conversation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConversationResource {
    private ConversationService service;
    private SessionService sessionService;

    public ConversationResource(ConversationService service, SessionService sessionService) {
        this.service = service;
        this.sessionService = sessionService;
    }

    @UnitOfWork
    @GET
    public List<Conversation> list(@HeaderParam("X-Auth-Token") String token){
        return service.conversationsFor(getUser(token));
    }

    private long getUser(String token){
        Session session = sessionService.loginWithToken(token);
        return session.getUserId();
    }
}
