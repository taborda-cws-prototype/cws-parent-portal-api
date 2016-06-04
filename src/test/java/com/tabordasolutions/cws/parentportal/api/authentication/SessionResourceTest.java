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
    SessionForm form;

    @Before
    public void setup(){
        validUserName = "able";
        validPassword = "abc";
        inValidUserName = "Able";
        inValidPassword = "ABC";

        SessionService service = new SessionService();
        resource = new SessionResource(service);

        form = new SessionForm();
    }

    @Test
    public void testSuccessfulLogin(){
        form.setEmail(validUserName);
        form.setPassword(validPassword);
        Session session = resource.login(form);
        assertTrue(session.isSuccess());
    }

    @Test
    public void testInvalidUserNameLogin(){
        form.setEmail(inValidUserName);
        form.setPassword(validPassword);
        Session session = resource.login(form);
        assertFalse(session.isSuccess());
    }

    @Test
    public void testInvalidPasswordLogin(){
        form.setEmail(validUserName);
        form.setPassword(inValidPassword);
        Session session = resource.login(form);
        assertFalse(session.isSuccess());
    }

    @Test
    public void testMissingLogin(){
        Session session = resource.login(form);
        assertFalse(session.isSuccess());
    }

    @Test
    public void testEmptyLogin(){
        form.setEmail("");
        form.setPassword("");
        Session session = resource.login(form);
        assertFalse(session.isSuccess());
    }
}