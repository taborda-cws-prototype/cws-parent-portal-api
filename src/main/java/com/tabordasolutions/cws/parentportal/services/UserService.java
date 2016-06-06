package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.UserDAO;

public class UserService {
    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public User findUserByUserName(String username){
        return dao.findByUsername(username);
    }

    public User findUserById(long id){
        return dao.find(id);
    }
}
