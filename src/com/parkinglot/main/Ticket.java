package com.parkinglot.main;

public class Ticket {
	private String ticketCode;
	private String position;
	
	public String getTicketCode() {
		return ticketCode;
	}
	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(int row, int column) {
		this.position = row + "," + column;
	}
	
	@Override
	public String toString() {
		return "Ticket [ticketCode=" + ticketCode + ", position=" + position + "]";
	}
	
	
	
}
