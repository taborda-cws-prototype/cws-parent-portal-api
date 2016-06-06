package com.tabordasolutions.cws.parentportal.resources;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.services.ConversationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/conversation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConversationResource {
    private ConversationService service;

    public ConversationResource(ConversationService service) {
        this.service = service;
    }

    @GET
    public List<Conversation> list(){
        return service.conversationsFor(1);
    }
}
