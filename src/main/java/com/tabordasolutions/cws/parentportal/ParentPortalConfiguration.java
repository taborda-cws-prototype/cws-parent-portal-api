package com.tabordasolutions.cws.parentportal;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParentPortalConfiguration extends Configuration {

	/**
	 * The application name
	 */
	@NotEmpty
	private String applicationName;
	private DataSourceFactory dataSourceFactory;
	private FlywayFactory flywayFactory;
	
    /**
     * The URL to access CHHS Open Data API for Foster Agencies
     */
    @NotEmpty
    private String apiChhsUrl;

    /**
     * The key to access CHHS Open Data API for Foster Agencies
     */
    @NotEmpty
    private String apiChhsKey;
	
    
    @Valid
	@NotNull
	private JerseyClientConfiguration jerseyClientConfiguration = new JerseyClientConfiguration();

	@JsonProperty
	public String getApplicationName() {
		return applicationName;
	}

	@JsonProperty
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@JsonProperty
	public DataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}

	@JsonProperty
	public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
		this.dataSourceFactory = dataSourceFactory;
	}

	@JsonProperty
	public FlywayFactory getFlywayFactory() {
		return flywayFactory;
	}

	@JsonProperty
	public void setFlywayFactory(FlywayFactory flywayFactory) {
		this.flywayFactory = flywayFactory;
	}
	
    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClientConfiguration;
    }

    @JsonProperty("api.chhs.url")
	public String getApiChhsUrl() {
		return apiChhsUrl;
	}

    @JsonProperty("api.chhs.url")
	public void setApiChhsUrl(String apiChhsUrl) {
		this.apiChhsUrl = apiChhsUrl;
	}

	@JsonProperty("api.chhs.key")
	public String getApiChhsKey() {
		return apiChhsKey;
	}

	@JsonProperty("api.chhs.key")
	public void setApiChhsKey(String apiChhsKey) {
		this.apiChhsKey = apiChhsKey;
	}
}