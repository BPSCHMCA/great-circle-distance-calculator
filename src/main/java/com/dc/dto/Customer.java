package com.dc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {

	@JsonProperty("user_id")
	private int userId;

	private String name;

	private double latitude;

	private double longitude;

	private double distance;

	public Customer() {
		super();
	}

	public Customer(int userId, String name, String latitude, String longitude) {
		super();
		this.userId = userId;
		this.name = name;
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", distance=" + distance + "]";
	}

}
