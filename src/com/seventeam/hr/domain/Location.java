package com.seventeam.hr.domain;
public class Location {

	private int locationId;
	String StreetAddress;
	String postalCode;
	String city;
	String stateProvince;
	String countryId;
	
	public int getLocationId() {
		return locationId;
	}
	
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

}
