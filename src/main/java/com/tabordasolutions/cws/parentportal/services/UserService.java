package com.tabordasolutions.cws.parentportal.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.api.UserDAO;

public class UserService {
	public static final Logger LOGGER = LoggerFactory
			.getLogger(UserService.class);
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
    
    /**
     * Updates the given user.  Ensures user exists and email is unique.
     * 
     * @param id	The id of the user
     * @param user	The user to be updated
     * @return		The updated user
     */
    public User updateUser(long id, User user) {
    	User existingUser = findUserById(id);
    	//TODO : let db handle checks below.  Use ExceptionMapper.
    	if(existingUser == null) {
    		LOGGER.warn("User with id:{} not found", id);
    		throw new ServicesException("User does not exist");
    	}
    	User existingWithUsername = findUserByUserName(user.getEmail());
    	if( existingWithUsername != null && existingWithUsername.getId() != id) {
    		LOGGER.warn("Unable to update user email to non unique email {}", user.getEmail());
    		throw new ServicesException("email not unique");
    	}
    	
    	
    	return dao.update(copyUser(existingUser, user));
    }

    /**
     * Creates a new user. Ensures user does not already exist.
     * 
     * @param user	The user to be created
     * @return	The created user ( new id is populated )
     */
    public long createUser(User user) {
    	//TODO : let db handle check below.  Use ExceptionMapper.
    	User existingUser = findUserByUserName(user.getEmail());
    	if( existingUser == null ) {
    		return dao.create(user);	
    	} else {
    		LOGGER.warn("Unable to create user with non unique email {}", user.getEmail());
    		throw new ServicesException("email not unique" );
    	}
    }
    
    private User copyUser(User user, User copyFrom) {
    	user.setFirstName(copyFrom.getFirstName());
    	user.setLastName(copyFrom.getLastName());
    	user.setInCareOf(copyFrom.getInCareOf());
    	user.setStreetAddress1(copyFrom.getStreetAddress1());
    	user.setStreetAddress2(copyFrom.getStreetAddress2());
    	user.setState(copyFrom.getState());
    	user.setCity(copyFrom.getCity());
    	user.setZip(copyFrom.getZip());
    	user.setImageUrl(copyFrom.getImageUrl());
    	user.setEmail(copyFrom.getEmail());
    	user.setPassword(copyFrom.getPassword());
    	
    	return user;
    }
}
