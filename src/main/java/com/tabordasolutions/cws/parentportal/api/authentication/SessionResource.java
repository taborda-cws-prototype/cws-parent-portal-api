package com.tabordasolutions.cws.parentportal.api.authentication;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionResource {

    public Session login(@PathParam("email") String email, @PathParam("password") String password){
        boolean valid = false;
        if (isValid(email) && isValid(password)){
           valid = true;
        }
        return new Session(valid, "");
    }

    @POST
    private boolean isValid(String string){
        return string != null && string.length() > 0 && Character.isLowerCase(string.charAt(0));
    }
}
