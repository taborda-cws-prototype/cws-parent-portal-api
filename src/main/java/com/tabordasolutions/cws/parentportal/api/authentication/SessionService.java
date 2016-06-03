package com.tabordasolutions.cws.parentportal.api.authentication;

public class SessionService {
    public boolean login(String username, String password ){

        boolean valid = false;
        if (isValid(username) && isValid(password)){
            valid = true;
        }
        return valid;
    }

    private boolean isValid(String string){
        return string != null && string.length() > 0 && Character.isLowerCase(string.charAt(0));
    }
}
