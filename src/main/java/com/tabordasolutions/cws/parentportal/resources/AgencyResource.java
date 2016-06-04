package com.tabordasolutions.cws.parentportal.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tabordasolutions.cws.parentportal.api.Agency;

@Path("agencies/{zipcode}")
@Produces(MediaType.APPLICATION_JSON)
public interface AgencyResource {
	
	@GET
	@UnitOfWork
	public List<Agency> listAgencies();

}
