package com.tabordasolutions.cws.parentportal.api.messaging;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class MessageTest {
    private long id;
    private Date date;
    private String subject;
    private String body;

    @Before
    public void setup(){
        id = 1;
        date = new Date();
        subject = "A nifty message";
        body = "I was wondering if the previous message I sent to you made sense";
    }

    @Test
    public void testPropertiesAreSetViaConstructor(){
        Message message = new Message(id, date, subject, body);
        assertEquals(message.getId(), id);
        assertEquals(message.getDate(), date);
        assertEquals(message.getSubject(), subject);
        assertEquals(message.getBody(), body);
    }
}