package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.UserDAO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService service;
    private final User user = mock(User.class);

    @Before
    public void setup(){
        UserDAO dao = mock(UserDAO.class);
        when(dao.findByUsername("joey.doe@example.com")).thenReturn(user);
        service = new UserService(dao);
    }

    @Test
    public void testRetrievingUserByIdWhenUserExists(){
        assertEquals("Expect to find user with name", "Fred", service.findUserById(1).getFirstName());
        assertEquals("Expect to find user with name", "Flinstone", service.findUserById(1).getLastName());
    }

    @Test
    public void testRetrievingUserByEmailWhenUserExists(){
        assertEquals(user, service.findUserByUserName("joey.doe@example.com"));
    }
}