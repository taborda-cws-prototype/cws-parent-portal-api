package com.tabordasolutions.cws.parentportal.services;

import org.junit.Test;

import com.tabordasolutions.cws.parentportal.services.MessageService;

import java.util.List;

import static org.junit.Assert.*;

public class MessageServiceTest {
    @Test
    public void testRetrievingListOfMessages(){
        MessageService service = new MessageService();
        List messages = service.messagesFor(1);
        assertFalse(messages.isEmpty());
    }
}