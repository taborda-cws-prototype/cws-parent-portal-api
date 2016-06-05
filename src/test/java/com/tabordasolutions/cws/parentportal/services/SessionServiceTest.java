package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SessionServiceTest {
    private SessionService service;

    @Before
    public void setup(){
        UserService service = mock(UserService.class);
        User user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(service.findUserByUserName("joey.doe@example.com")).thenReturn(user);
        this.service = new SessionService(service);
    }

    @Test
    public void loginWithValidCredentialsReturnsValidSession(){
        assertTrue(service.login("joey.doe@example.com", "password1").isSuccess());
    }

    @Test
    public void loginWithValidCredentialsReturnsSessionWithUserId(){
        assertEquals(1, service.login("joey.doe@example.com", "password1").getUserId());
    }

    @Test
    public void loginWithInvalidUserReturnsInvalidSession(){
        assertFalse(service.login("bogus@example.com", "password1").isSuccess());
    }

    @Test
    public void loginWithInvalidPasswordReturnsInvalidSession(){
        assertFalse(service.login("joey.doe@example.com", "BOGUS").isSuccess());
    }
}