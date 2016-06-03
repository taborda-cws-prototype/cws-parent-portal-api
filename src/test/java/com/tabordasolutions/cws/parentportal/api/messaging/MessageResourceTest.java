package com.tabordasolutions.cws.parentportal.api.messaging;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class MessageResourceTest {

    @Test
    public void testRetreivalForListOfMessages(){
        MessageService service = new MessageService();
        MessageResource resource = new MessageResource(service);
        List messages = resource.list(1);
        assertFalse(messages.isEmpty());
    }

}