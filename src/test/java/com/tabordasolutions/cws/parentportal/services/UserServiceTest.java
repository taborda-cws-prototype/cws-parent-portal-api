package com.tabordasolutions.cws.parentportal.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService service;
    @Before
    public void setup(){
        service = new UserService();
    }

    @Test
    public void testRetrievingUserByIdWhenUserExists(){
        assertEquals("Expect to find user with name", "Fred", service.findUserById(1).getFirstName());
        assertEquals("Expect to find user with name", "Flinstone", service.findUserById(1).getLastName());
    }

    @Test
    public void testRetrievingUserByEmailWhenUserExists(){
        assertEquals("Expect to find user with name", "Fred", service.findUserByUserName("fred@bedrock.comx").getFirstName());
        assertEquals("Expect to find user with name", "Flinstone", service.findUserByUserName("fred@bedrock.comx").getLastName());
    }
}