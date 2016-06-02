package com.tabordasolutions.cws.parentportal.api.authentication;

public class Session {

    private boolean success;
    private String token;

    public Session(boolean success, String token) {
        this.success = success;
        this.token = success ? token : "";
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }
}
