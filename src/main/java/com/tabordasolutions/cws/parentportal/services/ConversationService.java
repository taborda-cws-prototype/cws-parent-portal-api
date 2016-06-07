package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.*;

import java.util.ArrayList;
import java.util.List;

public class ConversationService {
    private ConversationDAO conversationDao;
    private MessageDAO messageDAO;

    public ConversationService(ConversationDAO conversationDao, MessageDAO messageDAO) {
        this.conversationDao = conversationDao;
        this.messageDAO = messageDAO;
    }

    public List<Conversation> conversationsAsRecipients(User user){
        List<Conversation> conversations = new ArrayList<Conversation>();

        List<Message> recipients = messageDAO.findMessagesByRecipient(user);

        for(Message message : recipients){
            Conversation conversation = message.getConversation();
            conversation.updateMessage(conversation.getBaseMessage());
            conversations.add(conversation);
        }

        return conversations;
    }

    public List<Conversation> conversationsAsSender(User user){
        List<Conversation> conversations = new ArrayList<Conversation>();

        List<Message> senders = messageDAO.findMessagesBySender(user);

        for(Message message : senders){
            Conversation conversation = message.getConversation();
            conversation.updateMessage(conversation.getBaseMessage());
            conversations.add(conversation);
        }

        return conversations;
    }

    public Conversation save(Conversation conversation, User sender, User recipient){
        Message message = new Message();
        message.setAuthor(sender);
        message.setRecipient(recipient);
        message.setDateCreated(conversation.getDateCreated());
        message.setSubject(conversation.getSubject());
        message.setBody(conversation.getInitializer());
        Conversation savedConversation = conversationDao.create(conversation);
        return savedConversation;
    }

    public Conversation find(long id){
        return conversationDao.find(id);
    }
}
