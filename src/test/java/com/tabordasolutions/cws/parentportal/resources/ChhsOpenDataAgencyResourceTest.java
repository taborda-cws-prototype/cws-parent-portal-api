package com.tabordasolutions.cws.parentportal.resources;

import java.util.List;

import javax.ws.rs.client.Client;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.*;

import com.tabordasolutions.cws.parentportal.api.Agency;
import com.tabordasolutions.cws.parentportal.resources.ChhsOpenDataAgencyResource;

public class ChhsOpenDataAgencyResourceTest {
	private ChhsOpenDataAgencyResource chhsOpenDataAgencyResource;
	
	private Client client;
	private String apiUrlString;
	private String apiKey;
	
    @Rule
    public ExpectedException thrown = ExpectedException.none();
	
    @Before
    public void setup() {
    	client = Mockito.mock(Client.class);
    	apiUrlString = "someApiUrlString";
    	apiKey = "someapikey";
    }
    
    @Test
    public void testListAgencies() {
    	chhsOpenDataAgencyResource = new ChhsOpenDataAgencyResource(client, apiUrlString, apiKey);
    	List<Agency> agencies = chhsOpenDataAgencyResource.listAgencies();
    	assertNotNull(agencies);
    	assertEquals(2, agencies.size());
    }
    
    public void testClientNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("client");
         
    	new ChhsOpenDataAgencyResource(null, apiUrlString, apiKey);
    }
    
    @Test
    public void testApiUrlNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("apiUrl");
        
    	new ChhsOpenDataAgencyResource(client, null, apiKey);
    }
    
    @Test
    public void testApiKeyNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("apiKey");
        
    	new ChhsOpenDataAgencyResource(client, apiUrlString, null);
    }
    
    @Test
    public void testApiUrlEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("apiUrl");
        
    	new ChhsOpenDataAgencyResource(client, "", apiKey);
    	
    }
    
    @Test
    public void testApiKeyEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("apiKey");
        
    	new ChhsOpenDataAgencyResource(client, apiUrlString, "");
    }

}
