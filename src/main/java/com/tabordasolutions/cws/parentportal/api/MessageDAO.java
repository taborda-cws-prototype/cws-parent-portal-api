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

    public Message findByConversation(Conversation conversation){
        if (conversation == null) { return new Message(); }
        Query query = currentSession().createQuery(
                "from Message M where M.conversation = :conversation");
        query.setEntity("conversation", conversation);
        return uniqueResult(query);
    }

    public List<Message> findMessagesByRecipient(User user){
        return findMessagesBy(user, "from Message M where M.recipient=:person", "person");
    }

    public List<Message> findMessagesBySender(User user){
        return findMessagesBy(user, "from Message M where M.author=:person", "person");
    }

    private List<Message> findMessagesBy(User user, String queryString, String userNamedParam){
        Query query = currentSession().createQuery( queryString);
        query.setEntity("person", user);
        return query.list();
    }
}
