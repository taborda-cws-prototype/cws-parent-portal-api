package com.tabordasolutions.cws.parentportal.api;

import com.tabordasolutions.cws.parentportal.api.authentication.SessionResource;
import com.tabordasolutions.cws.parentportal.api.authentication.SessionService;
import com.tabordasolutions.cws.parentportal.api.messaging.MessageResource;
import com.tabordasolutions.cws.parentportal.api.messaging.MessageService;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tabordasolutions.cws.parentportal.api.resources.ParentPortalResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class ParentPortalApplication extends Application<ParentPortalConfiguration> {

    private FlywayBundle flywayBundle;
    public static final Logger LOGGER = LoggerFactory
            .getLogger(ParentPortalApplication.class);

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

        final SessionResource sessionResource = new SessionResource(new SessionService());
        environment.jersey().register(sessionResource);

        final MessageResource messageResource = new MessageResource(new MessageService());
        environment.jersey().register(messageResource);
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
