package com.tabordasolutions.cws.parentportal.resources;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.tabordasolutions.cws.parentportal.ShowConversationRequest;
import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.api.CreateConversationRequest;
import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.response.ConversationResponse;
import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;
import org.junit.Before;
import org.junit.Test;

public class ConversationResourceTest {
    private ConversationResource resource;
    private String token = "myToken";
    ConversationService mockedConversationService;
    Session session;
    UserService mockUserService;
    SessionService mockedSessionService;
    Conversation successfulConversation;
    Conversation unsavedConversation;
    User sender;
    User receiver;


    @Before
    public void setup(){
        sender = new User();
        sender.setId(7L);
        receiver = new User();
        receiver.setId(8L);
        unsavedConversation = new Conversation();
        successfulConversation = new Conversation();
        successfulConversation.setId(100);
        session = new Session(true, sender.getId(), token);

        mockedConversationService = mock(ConversationService.class);
        when(mockedConversationService.save((Conversation)any(),(User)any(),(User)any())).thenReturn(successfulConversation);

        mockedSessionService = mock(SessionService.class);
        when(mockedSessionService.loginWithToken(token)).thenReturn(session);

        mockUserService = mock(UserService.class);
        when(mockUserService.findUserById(sender.getId())).thenReturn(sender);
        when(mockUserService.findUserById(receiver.getId())).thenReturn(receiver);

        resource = new ConversationResource(mockedConversationService, mockedSessionService, mockUserService);
    }

    @Test
    public void listOfConverstaionsForRecipients(){
        resource.list("receiver", token);
        verify(mockedConversationService).conversationsAsRecipients(sender);
    }

    @Test
    public void listOfConverstaionsForSenders(){
        resource.list("sender", token);
        verify(mockedConversationService).conversationsAsSender(sender);
    }

    @Test
    public void createConversationIsSaved(){
        CreateConversationRequest request = new CreateConversationRequest(receiver.getId(), unsavedConversation.getSubject(), unsavedConversation.getInitializer());
        ConversationResponse response = resource.create(token, request);
        verify(mockedConversationService).save(unsavedConversation, sender, receiver);
        assertEquals("expect response to contain the saved conversation", successfulConversation, response.getConversation());
    }

    @Test
    public void successfulCreateConversationReturnsSuccess(){
        CreateConversationRequest request = new CreateConversationRequest(receiver.getId(), unsavedConversation.getSubject(), unsavedConversation.getInitializer());
        ConversationResponse response = resource.create(token, request);
        assertTrue("Expect response to indicate success", response.isSuccess());
    }

    @Test
    public void unsucessfulCreateConversationReturnsUnsuccess(){
        mockedConversationService = mock(ConversationService.class);
        when(mockedConversationService.save(unsavedConversation, sender, receiver)).thenReturn(unsavedConversation);
        resource = new ConversationResource(mockedConversationService, mockedSessionService, mockUserService);

        CreateConversationRequest request = new CreateConversationRequest(receiver.getId(), unsavedConversation.getSubject(), unsavedConversation.getInitializer());
        ConversationResponse response = resource.create(token, request);
        assertFalse("Expect status to indicate failure", response.isSuccess());
    }
    @Test
    public void createConversationReturnsSavedConverstaion(){
        Conversation conversation = new Conversation();
        when(mockedConversationService.save(conversation, sender, receiver)).thenReturn(conversation);
        CreateConversationRequest request = new CreateConversationRequest(receiver.getId(), unsavedConversation.getSubject(), unsavedConversation.getInitializer());
        ConversationResponse response = resource.create(token, request);
        assertEquals("Expect response to contain conversation", conversation, response.getConversation());
    }

    @Test
    public void findSavedConversation(){
        ShowConversationRequest request = new ShowConversationRequest();
        request.setId(7);
        request.setToken(token);
        resource.show(request);
        verify(mockedConversationService).find(sender.getId());
    }
}
