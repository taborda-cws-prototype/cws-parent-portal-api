package com.tabordasolutions.prototype;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.prototype.resources.ApplicationResource;

public class ApiApplication extends Application<ApiConfiguration> {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(ApiApplication.class);

	public static void main(final String[] args) throws Exception {
		new ApiApplication().run(args);
	}

	@Override
	public void run(final ApiConfiguration configuration,
			final Environment environment) throws Exception {
		LOGGER.info("Application name: {}", configuration.getApplicationName());
		
		final ApplicationResource applicationResource = new ApplicationResource(configuration.getApplicationName()) ;
		environment.jersey().register(applicationResource);
	}

}
