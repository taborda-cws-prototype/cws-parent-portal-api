package com.tabordasolutions.cws.parentportal.resources;

import static org.mockito.Mockito.*;
import com.tabordasolutions.cws.parentportal.services.ConversationService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConversationResourceTest {
    private ConversationResource resource;


    @Before
    public void setup(){

    }

    @Test
    public void listOfConverstaions(){
        ConversationService mockedService = mock(ConversationService.class);
        resource = new ConversationResource(mockedService);
        resource.list();
        verify(mockedService).conversationsFor(anyInt());
    }

}