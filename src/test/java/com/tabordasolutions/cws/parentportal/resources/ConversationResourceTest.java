package com.tabordasolutions.cws.parentportal.resources;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.tabordasolutions.cws.parentportal.services.ConversationService;

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