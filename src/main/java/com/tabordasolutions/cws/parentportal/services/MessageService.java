package com.tabordasolutions.cws.parentportal.services;

import java.util.*;

import com.tabordasolutions.cws.parentportal.api.Message;
import com.tabordasolutions.cws.parentportal.api.MessageDAO;
import com.tabordasolutions.cws.parentportal.api.User;

public class MessageService {
    MessageDAO messageDAO;

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public Message save(Message message){
        return messageDAO.create(message);
    }

    public Message find(long id){
        return messageDAO.find(id);
    }

    public List<Message> findByRecipient(User recipient){
        return messageDAO.findMessagesByRecipient(recipient);
    }

    public List<Message> findBySender(User sender){
        return messageDAO.findMessagesBySender(sender);
    }

    public List<Message> messagesFor(long userId){
        return null;
    }
}
