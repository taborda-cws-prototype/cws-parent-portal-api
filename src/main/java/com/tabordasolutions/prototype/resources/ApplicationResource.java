package com.tabordasolutions.prototype.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/application")
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationResource.class);
	
	private String applicationName;
	
	public ApplicationResource(String applicationName) {
		this.applicationName = applicationName;
	}
	
	@GET
	public String getApplicationName() {
		return applicationName;
	}
}
