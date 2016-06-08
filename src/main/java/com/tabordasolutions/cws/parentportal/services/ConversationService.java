package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.*;

import java.util.ArrayList;
import java.util.Date;
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
        conversation.setDateCreated(new Date());
        Message message = createInitialMessageForConversation(conversation, sender, recipient);
        conversation = addMessagePropertiesToConversation(conversation,message);
        Conversation savedConversation = conversationDao.create(conversation);
        messageDAO.create(message);
        return savedConversation;
    }

    private Message createInitialMessageForConversation(Conversation conversation, User sender, User recipient){
        Message message = new Message();
        message.setAuthor(sender);
        message.setRecipient(recipient);
        message.setDateCreated(conversation.getDateCreated());
        message.setSubject(conversation.getSubject());
        message.setBody(conversation.getInitializer());
        message.setConversation(conversation);
        return message;
    }

    private Conversation addMessagePropertiesToConversation(Conversation conversation, Message message){
        conversation.setSender(message.getAuthor().getFullName());
        conversation.setReceiver(message.getRecipient().getFullName());
        conversation.setSubject(message.getSubject());
        conversation.setBaseMessage(message);
        conversation.setInitializer(message.getAuthor().getFullName());
        return conversation;
    }

    public Conversation find(long id, User user ){
        Conversation conversation = conversationDao.find(id);

        if (conversationContainsUser(user, conversation)) {
            addMessagePropertiesToConversation(conversation, conversation.getBaseMessage());
        }else{
            conversation = new Conversation();
        }

        return conversation;
    }

    private boolean conversationContainsUser(User user, Conversation conversation){
        if (user == null || conversation == null) { return false; }
        if (conversation.getBaseMessage() == null) { return false; }
        return matchesUser(user, conversation.getBaseMessage().getAuthor()) || matchesUser(user, conversation.getBaseMessage().getRecipient());
    }
    private boolean matchesUser(User user, User messenger){
        if ( user == null || messenger == null) { return false; }
        if ( user.getId() == null || user.getId() <= 0 || messenger.getId() <= 0) { return false; }
        return user.getId() == messenger.getId();
    }
}
