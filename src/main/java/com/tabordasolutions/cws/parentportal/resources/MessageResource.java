package com.tabordasolutions.cws.parentportal.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.CreateMessageRequest;
import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.response.MessageResponse;
import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.MessageService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
    private String RECEIVER = "receiver";
    private MessageService messageService;
    private ConversationService conversationService;
    private SessionService sessionService;
    private UserService userService;

    public MessageResource() { }

    public MessageResource(MessageService messageService, ConversationService conversationService, SessionService sessionService, UserService userService) {
        this.messageService = messageService;
        this.conversationService = conversationService;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @UnitOfWork
    @Path("/")
    @POST
    public MessageResponse create(@HeaderParam("X-Auth-Token") String token, CreateMessageRequest createRequest){
        Conversation conversation = conversationService.find(createRequest.getConversationId(),sessionService.getUserByToken(token));
        Message message = createRequest.buildMessage(sessionService.getUserByToken(token) , sessionService.getUser(createRequest.getReceiver()), conversation);
        log("received request to create message for: " + message);
        log("creating message for conversation" + conversation);
        Message createdMessage = messageService.save(message);
        log("saved message to conversation" + conversation);
        boolean success = createdMessage.getId() > 0 ? true : false;
        return new MessageResponse(createdMessage, success );
    }

    @UnitOfWork
    @GET
    @Path("/{id}")
    public MessageResponse show(@PathParam("id") long id, @HeaderParam("X-Auth-Token" )String token){
        Message message =  messageService.find(id);
        return new MessageResponse(message, message.getId() > 0 );
    }

    @UnitOfWork
    @Path("/")
    @GET
    public List<Message> list(@QueryParam("select")String type, @HeaderParam("X-Auth-Token") String token){
        if (type == RECEIVER){
            return messageService.findByRecipient(sessionService.getUserByToken(token));

        }else{
            return messageService.findBySender(sessionService.getUserByToken(token));
        }
    }
    private void log(String message){
        System.out.println(message);
    }
}
