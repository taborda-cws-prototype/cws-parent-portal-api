package com.tabordasolutions.cws.parentportal.api;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.cws.parentportal.api.resources.ParentPortalResource;

public class ParentPortalApplication extends Application<ParentPortalConfiguration> {

    private FlywayBundle flywayBundle;
	public static final Logger LOGGER = LoggerFactory
			.getLogger(ParentPortalApplication.class);

	public static void main(final String[] args) throws Exception {
		new ParentPortalApplication().run(args);
	}

	@Override
    public void initialize(Bootstrap<ParentPortalConfiguration> bootstrap) {
        bootstrap.addBundle(getFlywayBundle());
    }

    public FlywayBundle<ParentPortalConfiguration> getFlywayBundle() {
        return  flywayBundle  != null ? flywayBundle : new FlywayBundle<ParentPortalConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ParentPortalConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public FlywayFactory getFlywayFactory(ParentPortalConfiguration configuration) {
                return configuration.getFlywayFactory();
            }
        };
    }
	public void setFlywayBundle(FlywayBundle<ParentPortalConfiguration> bundle) { this.flywayBundle = bundle; }

	@Override
	public void run(final ParentPortalConfiguration configuration, final Environment environment) throws Exception {
		LOGGER.info("Application name: {}", configuration.getApplicationName());
		final ParentPortalResource applicationResource = new ParentPortalResource(configuration.getApplicationName()) ;
		environment.jersey().register(applicationResource);

        // flywayMigration(configuration);
	}

    private void flywayMigration(ParentPortalConfiguration configuration) {
        Flyway flyway = new Flyway();
        flyway.setDataSource( configuration.getDataSourceFactory(). getUrl(),
                              configuration.getDataSourceFactory(). getUser(),
                              configuration.getDataSourceFactory(). getPassword());
        flyway.migrate();
    }
}
