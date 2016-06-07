package com.tabordasolutions.cws.parentportal.api;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

public class MessageDAO extends AbstractDAO<Message> {

    public MessageDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Message create(Message message){
        return persist(message);
    }

    public Message find(long id){
        return get(id);
    }

    public List<Message> findMessagesByRecipient(User user){
        return findMessagesBy(user, "from Message M where M.recipient = :recipient", "recipient");
    }

    public List<Message> findMessagesBySender(User user){
        assert user != null;
        return findMessagesBy(user, "from Message M where M.author = :author", "author");
    }

    private List<Message> findMessagesBy(User user, String queryString, String userNamedParam){
        Query query = currentSession().createQuery( queryString);
        query.setEntity(userNamedParam, user);
        return list(query);
    }
}
