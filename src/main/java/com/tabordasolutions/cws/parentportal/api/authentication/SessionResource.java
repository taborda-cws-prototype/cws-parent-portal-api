package com.tabordasolutions.cws.parentportal.api.authentication;

import com.google.common.annotations.VisibleForTesting;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.PathParam;

public class SessionResource {


    public Session login(@PathParam("email") String email, @PathParam("password") String password){
        boolean valid = false;
        if (isValid(email) && isValid(password)){
           valid = true;
        }
        return new Session(valid, "");
    }

    private boolean isValid(String string){
        return string != null && string.length() > 0 && Character.isLowerCase(string.charAt(0));
    }
}
