package com.tabordasolutions.cws.parentportal.resources;

import com.tabordasolutions.cws.parentportal.ShowConversationRequest;
import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.CreateConversationRequest;
import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.response.ConversationResponse;
import com.tabordasolutions.cws.parentportal.auth.Session;
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
    private String RECEIVER = "receiver";
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
    public ConversationResponse create(@HeaderParam("X-Auth-Token") String token, CreateConversationRequest createRequest){
        Conversation conversation = new Conversation();
        conversation.setInitializer(createRequest.getMessage());
        conversation.setSubject(createRequest.getSubject());
        log("received request to create conversation for: " + conversation);

        Conversation createdConversation = conversationService.save(conversation, getUserByToken(token), getUser(createRequest.getReceiverId()));
        log("Saved conversation for request: " + conversation);
        boolean success = createdConversation.getId() > 0 ? true : false;
        return new ConversationResponse(createdConversation, success );
    }

    @UnitOfWork
    @Path("/")
    @GET
    public List<Conversation> list(@QueryParam("select")String type, @HeaderParam("X-Auth-Token") String token){
        if (type == RECEIVER){
            return conversationService.conversationsAsRecipients(getUserByToken(token));
        }else{
            return conversationService.conversationsAsSender(getUserByToken(token));
        }
    }

    @UnitOfWork
    @Path("/{id}")
    @GET
    public Conversation show(ShowConversationRequest request){
        return conversationService.find(request.getId());

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
