package com.tabordasolutions.cws.parentportal.api.resources;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParentPortalResourceTest {
    private ParentPortalResource applicationResource;

    private String applicationName = "appname";
    @Before
    public void setup() {
    	applicationResource = new ParentPortalResource(applicationName);
    }

    @Test
    public void testGetAppliationName() {
        String name = applicationResource.getApplicationName();
        Assert.assertEquals("ApplicationName not correct", applicationName, name);
    }

}
