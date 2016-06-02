package com.tabordasolutions.cws.parentportal.api.authentication;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SessionTest {
    private boolean validLogin = true;
    private boolean inValidLogin = false;
    String token = "token";
    String emptyToken = "";

    @Test
    public void sessionSuccessfulyCreated(){
        Session session = new Session(validLogin, token);
        assertTrue(session.isSuccess());
    }

    @Test
    public void sessionNotSuccessfulyCreated(){
        Session session = new Session(inValidLogin, token);
        assertFalse(session.isSuccess());
    }

    @Test
    public void successfulSessionContainsToken(){
        Session session = new Session(validLogin, token);
        assertEquals(session.getToken(), token);
    }

    @Test
    public void unSuccessfulSessionDoesNotContainsToken(){
        Session session = new Session(inValidLogin, token);
        assertEquals(session.getToken(), emptyToken);
    }
}