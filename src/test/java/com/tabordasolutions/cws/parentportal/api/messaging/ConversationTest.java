package com.tabordasolutions.cws.parentportal.api.messaging;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ConversationTest {
    private Conversation conversation;

    @Before
    public void setup(){
        conversation = new Conversation();
    }

    @Test
    public void testAllAttributesExist(){
        String subject = "Subject line";
        Message originalMessage = new Message(1, new Date(), "Barney", subject, "the body");
        Date createDate = new Date();
        Date modifiedDate = new Date();
        String initializer = "Fred";
        String sender = "Barney";
        String receiver = "Fred";
        List messages =  Arrays.asList(new Message(2, new Date(), subject, "Fred", "another body"), new Message(3, new Date(), "Barney", subject, "some other body"));

        conversation.setBaseMessage(originalMessage);
        conversation.setDateCreated(createDate);
        conversation.setDateUpdated(modifiedDate);
        conversation.setInitializer(initializer);
        conversation.setReceiver(receiver);
        conversation.setSender(sender);
        conversation.setMessages(messages);
        conversation.setRead(true);
        conversation.setSubject(subject);
        assertEquals(originalMessage, conversation.getBaseMessage());
        assertEquals(createDate, conversation.getDateCreated());
        assertEquals(modifiedDate, conversation.getDateUpdated());
        assertEquals(initializer, conversation.getInitializer());
        assertEquals(receiver, conversation.getReceiver());
        assertEquals(sender, conversation.getSender());
        assertEquals(messages, conversation.getMessages());
        assertEquals(subject, conversation.getSubject());
        assertEquals(true, conversation.isRead());
    }
}