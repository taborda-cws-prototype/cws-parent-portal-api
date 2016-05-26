package com.tabordasolutions.cws.parentportal.api;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.cws.parentportal.api.resources.ParentPortalResource;

public class ParentPortalApplication extends Application<ParentPortalConfiguration> {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(ParentPortalApplication.class);

	public static void main(final String[] args) throws Exception {
		new ParentPortalApplication().run(args);
	}

	@Override
	public void run(final ParentPortalConfiguration configuration,
			final Environment environment) throws Exception {
		LOGGER.info("Application name: {}", configuration.getApplicationName());
		
		final ParentPortalResource applicationResource = new ParentPortalResource(configuration.getApplicationName()) ;
		environment.jersey().register(applicationResource);
	}

}
