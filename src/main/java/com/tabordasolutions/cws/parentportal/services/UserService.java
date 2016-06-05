package com.tabordasolutions.cws.parentportal.services;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.UserDAO;

import java.util.Arrays;

public class UserService {
    public UserService(UserDAO dao) {
    }

    public User findUserByUserName(String username){
        return retrieveUser(1);
    }

    public User findUserById(long id){
        return retrieveUser(id);
    }

    private User retrieveUser(long id){
        User caseworker = createUser("Betty", "Ruble", "", "555 Bedrock", "", "AZ", "11111", "", "betty@bedrock.comx", "password");
        User user = createUser("Fred", "Flinstone", "Bam Bam", "123 Bedrock", "", "AZ", "11111", "http://i.dailymail.co.uk/i/pix/2015/10/17/19/2D81CC2500000578-3277267-image-m-5_1445105070585.jpg", "fred@bedrock.comx", "password");
        user.setCaseworkers(Arrays.asList(caseworker));
        return user;
    }

    private User createUser(String fname, String lname, String ico, String addr, String state, String city, String zip, String image, String email, String password){
        User user = new User();
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setInCareOf(ico);
        user.setStreetAddress1(addr);
        user.setStreetAddress2("");
        user.setState(state);
        user.setZip(zip);
        user.setImageUrl(image);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
