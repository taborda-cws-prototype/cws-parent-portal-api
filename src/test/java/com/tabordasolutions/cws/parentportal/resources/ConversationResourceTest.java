package com.tabordasolutions.cws.parentportal.resources;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import com.tabordasolutions.cws.parentportal.api.Conversation;
import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import org.junit.Before;
import org.junit.Test;


import javax.ws.rs.core.Response;

public class ConversationResourceTest {
    private ConversationResource resource;
    private String token = "myToken";
    ConversationService mockedService;
    Session session;
    SessionService mockedSessionService;
    int id = 1;

    @Before
    public void setup(){
        session = new Session(true, id, token);
        mockedService = mock(ConversationService.class);
        mockedSessionService = mock(SessionService.class);
        when(mockedSessionService.loginWithToken(token)).thenReturn(session);
        resource = new ConversationResource(mockedService, mockedSessionService);
    }

    @Test
    public void listOfConverstaions(){
        resource.list(token);
        verify(mockedService).conversationsFor(id);
    }

    @Test
    public void createConversationIsSaved(){
        Conversation conversation = new Conversation();
        resource.create(conversation);
        verify(mockedService).save(conversation);
    }

    @Test
    public void createConversationReturnsStatus(){
        Conversation conversation = new Conversation();
        Response response = resource.create(conversation);
        assertEquals("Expect response to be succesful", 201, response.getStatus());
    }

    @Test
    public void createConversationReturnsSavedConverstaion(){
        Conversation conversation = new Conversation();
        when(mockedService.save(conversation)).thenReturn(conversation);
        Response response = resource.create(conversation);
        assertEquals("Expect response to contain conversation", conversation, response.getEntity());
    }


}
