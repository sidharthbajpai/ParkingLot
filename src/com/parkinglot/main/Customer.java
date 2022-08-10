package com.parkinglot.main;

public class Customer {
	private Vehicle vehicle;
	private Ticket ticket;
	private String preferredParkingType;
	
	public Customer() {}

	public Customer(Vehicle vehicle, String preferredParkingType) {
		this.vehicle = vehicle;
		this.preferredParkingType = preferredParkingType;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getPreferredParkingType() {
		return preferredParkingType;
	}

	public void setPreferredParkingType(String preferredParkingType) {
		this.preferredParkingType = preferredParkingType;
	}

	@Override
	public String toString() {
		return "Customer [vehicle=" + vehicle + ", ticket=" + ticket + ", preferredParkingType=" + preferredParkingType
				+ "]";
	}

	
	
	

}
