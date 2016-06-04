package com.tabordasolutions.cws.parentportal.services;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

import com.tabordasolutions.cws.parentportal.api.Conversation;

public class MessageServiceTest {
    @Test
    public void testRetrievingListOfMessages(){
        MessageService service = new MessageService();
        List<Conversation> messages = service.messagesFor(1);
        assertFalse(messages.isEmpty());
    }
}