package com.tabordasolutions.cws.parentportal.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/application")
@Produces(MediaType.APPLICATION_JSON)
public class ParentPortalResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParentPortalResource.class);
	
	private String applicationName;
	
	public ParentPortalResource(String applicationName) {
		this.applicationName = applicationName;
	}
	
	@GET
	public String getApplicationName() {
		return applicationName;
	}
}
