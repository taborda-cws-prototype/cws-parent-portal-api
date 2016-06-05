package com.tabordasolutions.cws.parentportal;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.cws.parentportal.resources.AgencyResource;
import com.tabordasolutions.cws.parentportal.resources.ChhsOpenDataAgencyResource;
import com.tabordasolutions.cws.parentportal.resources.MessageResource;
import com.tabordasolutions.cws.parentportal.resources.ParentPortalResource;
import com.tabordasolutions.cws.parentportal.resources.SessionResource;
import com.tabordasolutions.cws.parentportal.resources.UserResource;
import com.tabordasolutions.cws.parentportal.services.MessageService;
import com.tabordasolutions.cws.parentportal.services.SessionService;
import com.tabordasolutions.cws.parentportal.services.UserService;

public class ParentPortalApplication extends
		Application<ParentPortalConfiguration> {

	public static final Logger LOGGER = LoggerFactory
			.getLogger(ParentPortalApplication.class);
	
	private FlywayBundle<ParentPortalConfiguration> flywayBundle;

	public static void main(final String[] args) throws Exception {
		new ParentPortalApplication().run(args);
	}

    @Override
    public void initialize(Bootstrap<ParentPortalConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor()
                )
        );

        bootstrap.addBundle(getFlywayBundle());
    }

    public FlywayBundle<ParentPortalConfiguration> getFlywayBundle() {
        return flywayBundle != null ? flywayBundle : new FlywayBundle<ParentPortalConfiguration>() {
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

    public void setFlywayBundle(FlywayBundle<ParentPortalConfiguration> bundle) {
        this.flywayBundle = bundle;
    }

    @Override
    public void run(final ParentPortalConfiguration configuration, final Environment environment) throws Exception {
        LOGGER.info("Application name: {}", configuration.getApplicationName());

        LOGGER.info("Registering Application Resources");
        loadResources(configuration, environment);

        LOGGER.info("Configuring Flyway DB migration");
        flywayMigration(configuration);

        LOGGER.info("Configuring CORS: Cross-Origin Resource Sharing");
        configureCors(environment);
	}
    
    private void loadResources(final ParentPortalConfiguration configuration, final Environment environment){
        final ParentPortalResource applicationResource = new ParentPortalResource(configuration.getApplicationName());
        environment.jersey().register(applicationResource);

        UserService userService = new UserService();
        SessionService sessionService = new SessionService(userService);
        final SessionResource sessionResource = new SessionResource(sessionService);
        environment.jersey().register(sessionResource);

        final MessageResource messageResource = new MessageResource(new MessageService());
        environment.jersey().register(messageResource);

        final UserResource userResource = new UserResource(userService);
        environment.jersey().register(userResource);
        
        final AgencyResource agencyResource = agencyResource(configuration, environment);
		environment.jersey().register(agencyResource);
    }
    
	private AgencyResource agencyResource(ParentPortalConfiguration configuration, Environment environment) {

		final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClient())
                   .build(getName());
		return new ChhsOpenDataAgencyResource(client, configuration.getApiChhsUrl(), configuration.getApiChhsKey(), configuration.getApiChhsQueryParam());
	}

    private void flywayMigration(ParentPortalConfiguration configuration) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(configuration.getDataSourceFactory().getUrl(),
                configuration.getDataSourceFactory().getUser(),
                configuration.getDataSourceFactory().getPassword());
        flyway.migrate();
    }
    
    private void configureCors(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }
}
