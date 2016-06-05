package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.auth.Session;

public class SessionService {
    private UserService userService;

    public SessionService(UserService userService) {
        this.userService = userService;
    }

    public Session login(String username, String password) {
        User user = userService.findUserByUserName(username);
        if (user == null) {
            return new Session(false, -1);
        }
        return new Session(isValid(password), user.getId());
    }

    private boolean isValid(String string) {
        return string != null && string.length() > 0 && Character.isLowerCase(string.charAt(0));
    }
}
