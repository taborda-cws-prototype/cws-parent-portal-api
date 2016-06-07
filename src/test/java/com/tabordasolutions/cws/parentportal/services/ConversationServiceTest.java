package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.ConversationDAO;
import com.tabordasolutions.cws.parentportal.api.MessageDAO;
import com.tabordasolutions.cws.parentportal.api.User;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import com.tabordasolutions.cws.parentportal.api.Conversation;

public class ConversationServiceTest {
    ConversationService service;
    ConversationDAO mockedConversationDao;
    MessageDAO mockMessageDao;
    User sender;
    User receiver;

    @Before
    public void setup(){
        mockedConversationDao = mock(ConversationDAO.class);
        mockMessageDao = mock(MessageDAO.class);
        service = new ConversationService(mockedConversationDao, mockMessageDao);
        sender = new User();
        receiver = new User();
    }


    @Test
    public void testSavingConversation(){
        Conversation conversation = new Conversation();
        service.save(conversation, sender, receiver);
        verify(mockedConversationDao).create(conversation);
    }

    @Test
    public void testFindingConversation(){
        service.find(1);
        verify(mockedConversationDao).find(1);
    }

}