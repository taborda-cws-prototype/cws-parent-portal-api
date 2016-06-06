package com.tabordasolutions.cws.parentportal.services;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ConversationServiceTest {
    @Test
    public void searchingAConversationByIdReturnsAList(){
        ConversationService service = new ConversationService();
        List conversations = service.conversationsFor(1);
        assertEquals("Expect an empty array", new ArrayList(), conversations );
    }

}