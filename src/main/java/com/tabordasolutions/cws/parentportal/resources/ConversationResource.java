package com.tabordasolutions.cws.parentportal.resources;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/conversation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConversationResource {
    private ConversationService conversationService;
    private SessionService sessionService;

    public ConversationResource(ConversationService service, SessionService sessionService) {
        this.conversationService = service;
        this.sessionService = sessionService;
    }

    @POST
    public Response create(Conversation conversation){
        Conversation createdConversation = conversationService.save(conversation);
        //success true
        return Response.status(Response.Status.OK).entity(createdConversation).build();
    }

    @UnitOfWork
    @GET
    public List<Conversation> list(@HeaderParam("X-Auth-Token") String token){
        return conversationService.conversationsFor(getUser(token));
    }

    private long getUser(String token) {
        Session session = sessionService.loginWithToken(token);
        return session.getUserId();
    }
}
