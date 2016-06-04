package com.tabordasolutions.cws.parentportal.api;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Agency {
	@NotBlank
	private String facilityName;

	@NotBlank
	private String locationAddress;

	@NotBlank
	private String locationState;

	@NotBlank
	private String locationCity;

	@NotBlank
	private String locationZip;

	private String facilityPhone;

	@NotBlank
	private String facilityType;

	public Agency(String facilityName, String locationAddress,
			String locationState, String locationCity, String locationZip,
			String facilityPhone, String facilityType) {
		super();
		this.facilityName = facilityName;
		this.locationAddress = locationAddress;
		this.locationState = locationState;
		this.locationCity = locationCity;
		this.locationZip = locationZip;
		this.facilityPhone = facilityPhone;
		this.facilityType = facilityType;
	}

	@JsonProperty("facility_name")
	public String getFacilityName() {
		return facilityName;
	}

	@JsonProperty("location_address")
	public String getLocationAddress() {
		return locationAddress;
	}

	@JsonProperty("location_state")
	public String getLocationState() {
		return locationState;
	}

	@JsonProperty("location_city")
	public String getLocationCity() {
		return locationCity;
	}

	@JsonProperty("location_zip")
	public String getLocationZip() {
		return locationZip;
	}

	@JsonProperty("facility_phone")
	public String getFacilityPhone() {
		return facilityPhone;
	}

	@JsonProperty("facility_type")
	public String getFacilityType() {
		return facilityType;
	}

	@Override
	public String toString() {
		return "Agency [facilityName=" + facilityName + ", locationAddress="
				+ locationAddress + ", locationState=" + locationState
				+ ", locationCity=" + locationCity + ", locationZip="
				+ locationZip + ", facilityPhone=" + facilityPhone
				+ ", facilityType=" + facilityType + "]";
	}
}
