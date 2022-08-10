package com.parkinglot.main;

public class Vehicle {
	public static final String SIZE_SMALL = "small";
	public static final String SIZE_MEDIUM = "medium";
	public static final String SIZE_LARGE = "large"; 
	
	private String vehicleSize;

	public Vehicle(String vehicleSize) {
		this.vehicleSize = vehicleSize;
	}

	public String getVehicleSize() {
		return vehicleSize;
	}

	public void setVehicleSize(String vehicleSize) {
		this.vehicleSize = vehicleSize;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleSize=" + vehicleSize + "]";
	}
	
	
}
