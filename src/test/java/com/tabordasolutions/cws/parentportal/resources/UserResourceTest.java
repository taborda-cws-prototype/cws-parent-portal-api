package com.tabordasolutions.cws.parentportal.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.tabordasolutions.cws.parentportal.api.UserDAO;
import org.junit.Before;
import org.junit.Test;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.services.UserService;

public class UserResourceTest {
    private UserService service;
    private final User user = mock(User.class);

    @Before
    public void setup() {
        UserDAO dao = mock(UserDAO.class);
        when(dao.find(1)).thenReturn(user);
        when(dao.findByUsername("joey.doe@example.com")).thenReturn(user);
        service = new UserService(dao);
    }

    @Test
    public void userReturnsUserObject() {
        UserResource resource = new UserResource(service);
        assertEquals(user, resource.user(1));
    }
}