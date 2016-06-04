package com.tabordasolutions.cws.parentportal.api.user;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class UserResourceTest {
    @Test
    public void testRetieveUserInfo(){
        UserResource resource = new UserResource();
        User user = resource.user(5L);
        assertEquals("expected to receive our user firstname", "Fred", user.getFirstName());
        assertEquals("expected to receive our user lastname", "Flinstone", user.getLastName());
    }

}