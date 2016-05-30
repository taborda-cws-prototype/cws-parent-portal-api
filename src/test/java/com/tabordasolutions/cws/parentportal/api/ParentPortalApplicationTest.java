package com.tabordasolutions.cws.parentportal.api;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Configuration;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class ParentPortalApplicationTest {
    ParentPortalApplication app;

    @Before
    public void setup() {
        app = new ParentPortalApplication();
    }

    @Test
    public void initializeApplicationWithFlyWayBundle() throws Exception {
        FlywayBundle flywayBundle = new FlywayBundle() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(io.dropwizard.Configuration configuration) {
                return null;
            }
        };
        app.setFlywayBundle(flywayBundle);

        Bootstrap mockBootstrap = mock(Bootstrap.class);
        app.initialize(mockBootstrap);

        verify(mockBootstrap).addBundle(flywayBundle);
    }
}