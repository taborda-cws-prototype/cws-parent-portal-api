package com.tabordasolutions.cws.parentportal.api.messaging;

import java.util.List;

import static org.junit.Assert.*;

public class MessageServiceTest {
    public void testRetrievingListOfMessages(){
        MessageService service = new MessageService();
        List messages = service.messagesFor();
        assertFalse(messages.isEmpty());
    }
}