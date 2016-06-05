package com.tabordasolutions.cws.parentportal.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.services.UserService;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @UnitOfWork
    @Path("/{id}")
    @GET
    public User user(@PathParam("id") long id){
        return userService.findUserById(id);
    }
}
