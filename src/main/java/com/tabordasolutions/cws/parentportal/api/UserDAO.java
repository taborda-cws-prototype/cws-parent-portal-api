package com.tabordasolutions.cws.parentportal.api;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User findByUsername(String email) {
        Query query = currentSession().createQuery("from User U where U.email = :email");
        query.setString("email", email);
        return uniqueResult(query);
    }
}
