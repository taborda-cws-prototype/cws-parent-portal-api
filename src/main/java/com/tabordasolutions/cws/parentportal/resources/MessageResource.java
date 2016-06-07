package com.tabordasolutions.cws.parentportal.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.response.MessageResponse;
import com.tabordasolutions.cws.parentportal.services.MessageService;
import io.dropwizard.hibernate.UnitOfWork;

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
    public List<Message> list(){
        return messageService.messagesFor(getUserId());
    }

    @GET
    @Path("/{id}")
    public Message show(@PathParam("id") long id){
        return messageService.find(id);
    }

    @UnitOfWork
    @POST
    public MessageResponse create(Message message){
        Message createdMessage = messageService.save(message);
        boolean success = createdMessage.getId() > 0 ? true : false;
        return new MessageResponse(createdMessage, success );
    }

    private long getUserId() {
        return 1;
    }
}
