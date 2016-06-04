package com.tabordasolutions.cws.parentportal.api.messaging;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
    MessageService messageService;

    public MessageResource(MessageService service) {
        this.messageService = service;
    }

    @GET
    public List<Conversation> list(){
        return messageService.messagesFor(getUserId());
    }

    private long getUserId() {
        return 1;
    }
}
