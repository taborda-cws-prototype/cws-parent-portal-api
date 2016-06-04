package com.tabordasolutions.cws.parentportal.auth;

import java.util.UUID;

public class Session {

    private boolean success;
    private String token;

    public Session(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return generateToken();
    }

    private String generateToken(){
        return success ? UUID.randomUUID().toString() : "";
    }
}
