package com.tabordasolutions.cws.parentportal.resources;

import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.User;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.tabordasolutions.cws.parentportal.services.MessageService;

import java.util.Date;

public class MessageResourceTest {
    MessageResource resource;
    MessageService mockMessageService;
    Message unsavedMessage;
    Message savedMessage;

    @Before
    public void setup(){
        mockMessageService = mock(MessageService.class);
        resource = new MessageResource(mockMessageService);
        unsavedMessage = buildMessage();
        savedMessage = buildMessage();
        savedMessage.setId(8);
        when(mockMessageService.save(unsavedMessage)).thenReturn(savedMessage);
    }

    @Test
    public void testRetrievalForListOfMessages(){
        resource.list();
        verify(mockMessageService).messagesFor(1);
    }

    @Test
    public void testCreatingAMessage(){
        resource.create(unsavedMessage);
        verify(mockMessageService).save(unsavedMessage);
    }

    @Test
    public void testShowingAMessage(){
        resource.show(1);
        verify(mockMessageService).find(1);
    }

    private Message buildMessage(){
        return new Message(0L, new Date(), new User(), new User(), "String subject", "String body");
    }
}