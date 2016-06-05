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
        when(dao.find(1)).thenReturn(user);
        when(dao.findByUsername("joey.doe@example.com")).thenReturn(user);
        service = new UserService(dao);
    }

    @Test
    public void findUserByIdReturnsUser(){
        assertEquals(user, service.findUserById(1));
    }

    @Test
    public void findUserByUsernameReturnsUser(){
        assertEquals(user, service.findUserByUserName("joey.doe@example.com"));
    }
}