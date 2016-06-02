package com.tabordasolutions.cws.parentportal.api.authentication;

public class Session {

    private boolean success;

    public Session(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
