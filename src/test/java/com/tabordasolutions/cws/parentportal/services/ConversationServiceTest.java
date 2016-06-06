package com.tabordasolutions.cws.parentportal.services;

import org.junit.Before;
import org.junit.Test;

import com.tabordasolutions.cws.parentportal.api.Conversation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ConversationServiceTest {
    ConversationService service;

    @Before
    public void setup(){
        service = new ConversationService();

    }

    @Test
    public void searchingAConversationByIdReturnsAList(){
        List<Conversation> conversations = service.conversationsFor(1);
        assertEquals("Expect an empty array", new ArrayList<Conversation>(), conversations );
    }

    @Test
    public void testSavingConverstaion(){
        Conversation conversation = new Conversation();
        assertEquals("Expected to return saved converstaion", conversation, service.save(conversation));

    }

}