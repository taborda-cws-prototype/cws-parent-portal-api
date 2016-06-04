package com.tabordasolutions.cws.parentportal.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.services.UserService;

import java.util.Arrays;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @Path("/{id}")
    @GET
    public User user(@PathParam("id") long id){
        return userService.findUserById(id);
    }
}
