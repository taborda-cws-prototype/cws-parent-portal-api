package com.tabordasolutions.cws.parentportal.api;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ParentPortalConfiguration extends Configuration {

	@NotEmpty
	private String applicationName;

	@JsonProperty
	public String getApplicationName() {
		return applicationName;
	}

	@JsonProperty
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
