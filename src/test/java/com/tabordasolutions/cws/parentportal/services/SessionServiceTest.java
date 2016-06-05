package com.tabordasolutions.cws.parentportal.services;

import org.junit.Before;
import org.junit.Test;

public class SessionServiceTest {
    public String validUserName = "john@gmailx.com";
    public String validPassword = "goodPassword";
    public String inValidUserName = "john@gmailx.com";
    public String inValidPassword = "goodPassword";
    public SessionService service;

    @Before
    public void setup(){
        service = new SessionService(new UserService(null));
    }

    @Test
    public void testLoginForValidUser(){
        service.login(validUserName, validPassword);
    }

    @Test
    public void testLoginForInValidUser(){
        service.login(inValidUserName, validPassword);
    }

    @Test
    public void testLoginForInValidPassword(){
        service.login(validUserName, inValidPassword);
    }
}