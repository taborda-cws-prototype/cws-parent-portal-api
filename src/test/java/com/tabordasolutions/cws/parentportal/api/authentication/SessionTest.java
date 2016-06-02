package com.tabordasolutions.cws.parentportal.api.authentication;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SessionTest {

    @Test
    public void sessionSuccessfulyCreated(){
        Session session = new Session(true, "token");
        assertTrue(session.isSuccess());
    }

    @Test
    public void sessionNotSuccessfulyCreated(){
        Session session = new Session(false, "token");
        assertFalse(session.isSuccess());
    }

    @Test
    public void successfulSessionContainsToken(){
        String token = "token";
        Session session = new Session(true, token);
        assertEquals(session.getToken(), token);
    }

    @Test
    public void unSuccessfulSessionDoesNotContainsToken(){
        String token = "token";
        String emptyToken = "";
        Session session = new Session(false, token);
        assertEquals(session.getToken(), emptyToken);
    }
}