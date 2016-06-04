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
    long userId = 4;

    @Test
    public void sessionSuccessfulyCreated(){
        Session session = new Session(validLogin, userId);
        assertTrue(session.isSuccess());
    }

    @Test
    public void sessionNotSuccessfulyCreated(){
        Session session = new Session(inValidLogin, userId);
        assertFalse(session.isSuccess());
    }

    @Test
    public void successfulSessionContainsToken(){
        Session session = new Session(validLogin, userId);
        assertNotNull(session.getToken());
        assertEquals(session.getToken().length(), 36);
    }

    @Test
    public void unSuccessfulSessionDoesNotContainsToken(){
        Session session = new Session(inValidLogin, userId);
        assertEquals(session.getToken(), emptyToken);
    }

    @Test
    public void successfulSessionContainsUserId(){
        Session session = new Session(validLogin, userId);
        assertNotNull(session.getToken());
        assertEquals(session.getUserId(), userId);
    }

    @Test
    public void unSuccessfulSessionDoesNotContainsValidUserId(){
        Session session = new Session(inValidLogin, userId);
        assertEquals(session.getUserId(), 0);
    }
}