package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.auth.Cryptography;
import com.tabordasolutions.cws.parentportal.auth.Session;

public class SessionService {
    private UserService userService;
    private Cryptography cryptography;

    public SessionService(UserService userService, Cryptography cryptography) {
        this.userService = userService;
        this.cryptography = cryptography;
    }

    public Session login(String username, String password) {
        User user = userService.findUserByUserName(username);
        boolean valid = user == null ? false : isValid(password);
        long userId = user != null ? user.getId() : -1;
        return new Session(valid, userId, buildToken(username, password));
    }

    private boolean isValid(String string) {
        return string != null && string.length() > 0 && Character.isLowerCase(string.charAt(0));
    }

    private String buildToken(String userName, String password) {
        String phrase = userName + ":" + password;
        return cryptography.encrypt(phrase);

    }
}
