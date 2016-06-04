package com.tabordasolutions.cws.parentportal.api.messaging;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class MessageResourceTest {

    @Test
    public void testRetreivalForListOfMessages(){
        MessageService service = new MessageService();
        MessageResource resource = new MessageResource(service);
        List conversations = resource.list();
        assertFalse(conversations.isEmpty());
    }
}