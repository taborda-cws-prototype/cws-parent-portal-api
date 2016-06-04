package com.tabordasolutions.cws.parentportal.resources;

import org.junit.Test;

import com.tabordasolutions.cws.parentportal.resources.MessageResource;
import com.tabordasolutions.cws.parentportal.services.MessageService;

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