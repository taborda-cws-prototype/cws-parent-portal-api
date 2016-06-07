package com.tabordasolutions.cws.parentportal.resources;

import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.cws.parentportal.api.User;
import com.tabordasolutions.cws.parentportal.auth.Session;
import com.tabordasolutions.cws.parentportal.services.ServicesException;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	public static final Logger LOGGER = LoggerFactory
			.getLogger(UserResource.class);
	private UserService userService;
	private SessionService sessionService;
	
	public UserResource(UserService userService, SessionService sessionService) {
		this.userService = userService;
		this.sessionService = sessionService;
	}

	@UnitOfWork
	@Path("/me")
	@GET
	public User me(@HeaderParam("X-Auth-Token") String token) {
		Session session = sessionService.loginWithToken(token);
		return userService.findUserById(session.getUserId());
	}
	
	@UnitOfWork
	@Path("/{id}")
	@GET
	public User user(@PathParam("id") long id) {
		return userService.findUserById(id);
	}

	@UnitOfWork
	@Path("/username/{username}")
	@GET
	public User findUserByUserName(@PathParam("username") String username) {
		return userService.findUserByUserName(username);
	}

	@UnitOfWork
	@Path("/{id}")
	@PUT
	public User updateUser(@PathParam("id")long id, User user) {
		try {
			return userService.updateUser(id, user);
		} catch (ServicesException e) {
			//TODO : Handle through ExcpetionMapper
			LOGGER.error("Unable to update " + user, e);
			throw new ResourcesException(e.getMessage());
		}
	}

	@UnitOfWork
	@Path("/me")
	@PUT
	public User updateMe(@HeaderParam("X-Auth-Token") String token, User user) {
		Session session = sessionService.loginWithToken(token);
		try {
			return userService.updateUser(session.getUserId(), user);
		} catch (ServicesException e) {
			//TODO : Handle through ExcpetionMapper
			LOGGER.error("Unable to update " + user, e);
			throw new ResourcesException(e.getMessage());
		}
	}
	

	@UnitOfWork
	@Path("/")
	@POST
	public Session createUser(User user) {
		
		try {
			long newId = userService.createUser(user);
			
			User.Builder builder = new User.Builder();
			User newUser = builder.id(newId)
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.inCareOf(user.getInCareOf())
				.streetAddress1(user.getStreetAddress1())
				.streetAddress2(user.getStreetAddress2())
				.state(user.getState())
				.city(user.getCity())
				.zip(user.getZip())
				.imageUrl(user.getImageUrl())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();
			
			return sessionService.login(newUser.getEmail(), newUser.getPassword());
		} catch (ServicesException e) {
			//TODO : Handle through ExcpetionMapper
			LOGGER.error("Unable to create: " + user.toString(), e);
			throw new ResourcesException(e.getMessage());
		}
	}

	

}
