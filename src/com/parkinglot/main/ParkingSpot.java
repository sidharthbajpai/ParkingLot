package com.parkinglot.main;

public class ParkingSpot {
	public static final String TYPE_REGULAR = "regular";
	public static final String TYPE_HANDICAPPED = "handicapped";
	public static final String TYPE_COMPACT = "compact";
	
	private int positionX;
	private int positionY;
	private String type;
	private String size;
	private String ticketCode = "";
	private boolean isOccupied;

	public ParkingSpot(int positionX, int positionY, String type, String size) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.type = type;
		this.size = size;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	@Override
	public String toString() {
		return "ParkingSpot [positionX=" + positionX + ", positionY=" + positionY + ", type=" + type + ", size=" + size
				+ ", ticketCode=" + ticketCode + ", isOccupied=" + isOccupied + "]";
	}
	
	

}
