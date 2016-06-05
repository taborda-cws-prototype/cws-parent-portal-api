package com.tabordasolutions.cws.parentportal.api;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserDAOTest {
    // TODO: manage test database schema -- currently need to migrate the CI schema before pushing to GitHub
    // TODO: manage test fixtures -- currently need to see the database before pushing to GitHub
    @Test
    public void findUserByUsernameReturnsUser() throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();
        UserDAO dao = new UserDAO(sessionFactory);

        User user = dao.findByUsername("joey.doe@example.com");

        assertNotNull(user);
    }

    @Test
    public void findUserByUsernameHandlesUnknownUser() throws Exception {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();
        UserDAO dao = new UserDAO(sessionFactory);

        User user = dao.findByUsername("bogus@example.com");

        assertNull(user);
    }
}
