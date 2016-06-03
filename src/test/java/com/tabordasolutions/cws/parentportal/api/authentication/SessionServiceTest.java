package com.tabordasolutions.cws.parentportal.api.authentication;

import org.junit.Test;

import static org.junit.Assert.*;

public class SessionServiceTest {
    public String validUserName = "john@gmailx.com";
    public String validPassword = "goodPassword";
    public String inValidUserName = "john@gmailx.com";
    public String inValidPassword = "goodPassword";

    @Test
    public void testLoginForValidUser(){
        SessionService service = new SessionService();
        service.login(validUserName, validPassword);

    }

    @Test
    public void testLoginForInValidUser(){
        SessionService service = new SessionService();
        service.login(inValidUserName, validPassword);

    }

    @Test
    public void testLoginForInValidPassword(){
        SessionService service = new SessionService();
        service.login(validUserName, inValidPassword);

    }

}