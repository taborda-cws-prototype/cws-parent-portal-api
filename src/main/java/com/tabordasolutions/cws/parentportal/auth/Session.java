package com.tabordasolutions.cws.parentportal.auth;

import java.util.UUID;

public class Session {

    private boolean success;
    private long userId;

    public Session(boolean success, long userId) {
        this.success = success;
        if (success){
            this.userId = userId;
        }
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

    public long getUserId() { return userId; }
}
