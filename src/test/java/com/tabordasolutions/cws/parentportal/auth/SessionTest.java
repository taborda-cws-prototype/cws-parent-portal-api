package com.tabordasolutions.cws.parentportal.auth;

import org.junit.Before;
import org.junit.Test;

import com.tabordasolutions.cws.parentportal.auth.Session;

import static org.junit.Assert.*;

public class SessionTest {
    private boolean validLogin = true;
    private boolean inValidLogin = false;
    String token = "token";
    String emptyToken = "";

    @Test
    public void sessionSuccessfulyCreated(){
        Session session = new Session(validLogin);
        assertTrue(session.isSuccess());
    }

    @Test
    public void sessionNotSuccessfulyCreated(){
        Session session = new Session(inValidLogin);
        assertFalse(session.isSuccess());
    }

    @Test
    public void successfulSessionContainsToken(){
        Session session = new Session(validLogin);
        assertNotNull(session.getToken());
        assertEquals(session.getToken().length(), 36);
    }

    @Test
    public void unSuccessfulSessionDoesNotContainsToken(){
        Session session = new Session(inValidLogin);
        assertEquals(session.getToken(), emptyToken);
    }
}