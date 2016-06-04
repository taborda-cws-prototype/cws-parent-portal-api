package com.tabordasolutions.cws.parentportal.resources;

import java.util.List;

import javax.ws.rs.client.Client;

import jersey.repackaged.com.google.common.base.Preconditions;
import jersey.repackaged.com.google.common.collect.ImmutableList;

import com.tabordasolutions.cws.parentportal.api.Agency;

public class ChhsOpenDataAgencyResource implements AgencyResource {

	private final Client client;
	private final String apiKey;
	private final String apiUrl;
	
	public ChhsOpenDataAgencyResource(Client client, String apiUrl, String apiKey) {
		this.client = Preconditions.checkNotNull(client, "Client must not be null");
		this.apiUrl = Preconditions.checkNotNull(apiUrl, "apiUrl must not be null").trim();
		Preconditions.checkArgument(this.apiUrl.length() > 0, "apiUrl must not be empty");
		this.apiKey = Preconditions.checkNotNull(apiKey, "apiKey must not be null").trim();
		Preconditions.checkArgument(this.apiKey.length() > 0, "apiKey must not be empty");
	}
	
	@Override
	public List<Agency> listAgencies() {
		Agency agency1 = new Agency("facilityName1", "locationAddress1", "locationState1", "locationCity1", "locationZip1", "facilityType1");
		Agency agency2 = new Agency("facilityName2", "locationAddress2", "locationState2", "locationCity2", "locationZip2", "facilityType2");
		
		ImmutableList.Builder<Agency> builder = ImmutableList.builder();
		return builder.add( agency1 ).add( agency2 ).build();
	}
}
