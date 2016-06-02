package com.tabordasolutions.cws.parentportal.api.authentication;

import org.junit.Test;

import static org.junit.Assert.*;

public class SessionTest {

    @Test
    public void sessionSuccessfulyCreated(){
        Session session = new Session(true);
        assertTrue(session.isSuccess());
    }

    @Test
    public void sessionNotSuccessfulyCreated(){
        Session session = new Session(false);
        assertFalse(session.isSuccess());
    }
}