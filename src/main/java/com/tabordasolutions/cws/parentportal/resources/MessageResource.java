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
import org.hibernate.validator.cfg.context.GroupConversionTargetContext;

import java.util.ArrayList;
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
    @GET
    public List<Message> list(@QueryParam("select")String type, @HeaderParam("X-Auth-Token") String token){
        if (type == RECEIVER){
            return messageService.findByRecipient(getUserByToken(token));

        }else{
            return messageService.findBySender(getUserByToken(token));
        }
    }

    @UnitOfWork
    @GET
    @Path("/{id}")
    public Message show(@PathParam("id") long id, @HeaderParam("X-Auth-Token" )String token){
        return messageService.find(id);
    }

    @UnitOfWork
    @Path("/")
    @POST
    public MessageResponse create(@HeaderParam("X-Auth-Token") String token, CreateMessageRequest createRequest){
        Conversation conversation = conversationService.find(createRequest.getConversationId(),getUserByToken(token));
        Message message = createRequest.buildMessage(getUserByToken(token) , getUser(createRequest.getReceiver()), conversation);
        log("received request to create message for: " + message);
        log("creating message for conversation" + conversation);
        Message createdMessage = messageService.save(message);
        log("saved message to conversation" + conversation);
        boolean success = createdMessage.getId() > 0 ? true : false;
        return new MessageResponse(createdMessage, success );
    }

    private User getUserByToken(String token) {
        Session session = sessionService.loginWithToken(token);
        long userId = session.getUserId();
        return userService.findUserById(userId);
    }
    private User getUser(long id) {
        return userService.findUserById(id);
    }
    private void log(String message){
        System.out.println(message);
    }
}
