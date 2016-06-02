package com.tabordasolutions.cws.parentportal.api.messaging;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;

public class MessageResourceTest {

    @Test
    public void testRetreivalForListOfMessages(){
        MessageResource resource = new MessageResource();
        List messages = resource.list();
        assertFalse(messages.isEmpty());
    }

}