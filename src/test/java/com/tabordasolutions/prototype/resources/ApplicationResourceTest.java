package com.tabordasolutions.prototype.resources;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApplicationResourceTest {
    private ApplicationResource applicationResource;

    private String applicationName = "appname";
    @Before
    public void setup() {
    	applicationResource = new ApplicationResource(applicationName);
    }

    @Test
    public void testGetAppliationName() {
        String name = applicationResource.getApplicationName();
        Assert.assertEquals("ApplicationName not correct", applicationName, name);
    }

}
