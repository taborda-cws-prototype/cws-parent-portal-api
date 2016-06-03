package com.tabordasolutions.cws.parentportal.api.authentication;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SessionResourceTest {
    private String validUserName;
    private String validPassword;
    private String inValidUserName;
    private String inValidPassword;
    SessionResource resource;
    SessionService service;

    @Before
    public void setup(){
        validUserName = "able";
        validPassword = "abc";
        inValidUserName = "Able";
        inValidPassword = "ABC";

        SessionService service = new SessionService();
        resource = new SessionResource(service);
    }

    @Test
    public void testSuccessfulLogin(){
        Session session = resource.login(validUserName, validPassword);
        assertTrue(session.isSuccess());
    }

    @Test
    public void testInvalidUserNameLogin(){
        Session session = resource.login(inValidUserName, validPassword);
        assertFalse(session.isSuccess());
    }

    @Test
    public void testInvalidPasswordLogin(){
        Session session = resource.login(validUserName, inValidPassword);
        assertFalse(session.isSuccess());
    }

    @Test
    public void testMissingLogin(){
        Session session = resource.login(null, null);
        assertFalse(session.isSuccess());
    }

    @Test
    public void testEmptyLogin(){
        Session session = resource.login("", "");
        assertFalse(session.isSuccess());
    }
}