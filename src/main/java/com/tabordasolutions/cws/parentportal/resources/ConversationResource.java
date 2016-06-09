package com.tabordasolutions.cws.parentportal.resources;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.CreateConversationRequest;
import com.tabordasolutions.cws.parentportal.api.response.ConversationCreateResponse;
import com.tabordasolutions.cws.parentportal.api.response.ConversationListResponse;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/conversation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConversationResource {
    private String SENDER = "sender";
    private ConversationService conversationService;
    private SessionService sessionService;
    private UserService userService;

    public ConversationResource(ConversationService service, SessionService sessionService, UserService userService) {
        this.conversationService = service;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @UnitOfWork
    @Path("/")
    @POST
    public ConversationCreateResponse create(@HeaderParam("X-Auth-Token") String token, CreateConversationRequest createRequest){
        Conversation conversation = new Conversation();
        conversation.setInitializer(createRequest.getMessage());
        conversation.setSubject(createRequest.getSubject());
        log("received request to create conversation for: " + conversation);

        Conversation createdConversation = conversationService.save(conversation, sessionService.getUserByToken(token), sessionService.getUser(createRequest.getReceiverId()));
        log("Saved conversation for request: " + conversation);
        return new ConversationCreateResponse(createdConversation, isConversationSaved(createdConversation) );
    }

    @UnitOfWork
    @Path("/{id}")
    @GET
    public ConversationCreateResponse show(@PathParam("id") long id, @HeaderParam("X-Auth-Token" )String token){
        Conversation conversation = conversationService.find(id, sessionService.getUserByToken(token));
        return new ConversationCreateResponse(conversation, isConversationSaved(conversation) );
    }

    private boolean isConversationSaved(Conversation conversation){
        return conversation != null && conversation.getId() > 0;
    }

    @UnitOfWork
    @Path("/")
    @GET
    public ConversationListResponse list(@QueryParam("select")String type, @HeaderParam("X-Auth-Token") String token){
        List <Conversation>conversations;
        if (type.equals(SENDER)){
            conversations = conversationService.conversationsAsSender(sessionService.getUserByToken(token));
        }else{
            conversations = conversationService.conversationsAsRecipients(sessionService.getUserByToken(token));
        }

        return new ConversationListResponse(conversations, true);
    }

    private void log(String message){
        System.out.println(message);
    }
}
