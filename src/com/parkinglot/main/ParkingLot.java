package com.parkinglot.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class ParkingLot {
	private List<ParkingSpot> parkingSpots = new ArrayList<>();

	public ParkingLot(int regularSpots, int handicappedSpots, int compactSpots) {

		int total = regularSpots + handicappedSpots + compactSpots;
		int nearSquare = largestSquare(total);
		int squareRootOfNearSquare = ((Double) Math.sqrt(nearSquare)).intValue();
		int rows = squareRootOfNearSquare;
		int columns = squareRootOfNearSquare;

		List<String> type = new ArrayList<String>();
		for (int x = 0; x < total; x++) {
			if (x < regularSpots) {
				type.add(ParkingSpot.TYPE_REGULAR);
			} else if (x < regularSpots + handicappedSpots) {
				type.add(ParkingSpot.TYPE_HANDICAPPED);
			} else if (x < total) {
				type.add(ParkingSpot.TYPE_COMPACT);
			}
		}
		Collections.shuffle(type);

		String[] size = { Vehicle.SIZE_SMALL, Vehicle.SIZE_MEDIUM, Vehicle.SIZE_LARGE };
		Random random = new Random();
		for (int row = 1; row <= rows; row++) {
			for (int col = 1; col <= columns; col++) {
				int randonSizeIndex = random.nextInt(size.length);
				parkingSpots.add(new ParkingSpot(row, col, type.remove(0), size[randonSizeIndex]));
			}
		}

		int remainingCol = total - nearSquare;
		int tempCol = 0;
		for (int col = 1; col <= remainingCol; col++) {
			int randonSizeIndex = random.nextInt(size.length);
			int row = squareRootOfNearSquare + 1;
			if (col < squareRootOfNearSquare + 1) {
				parkingSpots.add(new ParkingSpot(row, col, type.remove(0), size[randonSizeIndex]));
			} else {
				if (tempCol == 0) {
					tempCol = col - 1;
				}
				parkingSpots.add(new ParkingSpot(row + 1, col - tempCol, type.remove(0), size[randonSizeIndex]));
			}
		}
	}

	public void print() {
		StringBuilder parkingBuilder = new StringBuilder();
		parkingBuilder.append("=============PARKING LOT======>\n\n");
		int currentRow = 1;
		for (ParkingSpot p : parkingSpots) {
			if (currentRow != p.getPositionX()) {
				parkingBuilder.append("\n\n");
				currentRow = p.getPositionX();
			}
			parkingBuilder.append(p.getPositionX()).append(",").append(p.getPositionY()).append(" ").append(p.getType())
					.append(" ").append(p.getSize()).append(" ").append(p.isOccupied() ? "Occupied" : "Unoccupied")
					.append("\t");
		}
		parkingBuilder.append("\n");
		System.out.println(parkingBuilder.toString());
	}

	private static int largestSquare(int total) {
		int i = 0;
		while ((i + 1) * (i + 1) < total) {
			++i;
		}
		return i * i;
	}

	public Customer parkThroughVelvet(Customer customer, boolean enteredFromBackGate) {
		String ticketCode = UUID.randomUUID().toString();
		Ticket ticket = new Ticket();
		if (enteredFromBackGate) {
			Collections.reverse(parkingSpots);
		}
		for (ParkingSpot p : parkingSpots) {
			if (!p.isOccupied() && p.getSize().equals(customer.getVehicle().getVehicleSize())
					&& p.getType().equals(customer.getPreferredParkingType())) {
				p.setOccupied(true);
				p.setTicketCode(ticketCode);
				ticket.setTicketCode(ticketCode);
				ticket.setPosition(p.getPositionX(), p.getPositionY());
				customer.setTicket(ticket);
				break;
			}
		}
		if (enteredFromBackGate) {
			Collections.reverse(parkingSpots);
		}
		if (customer.getTicket() == null) {
			System.out.println("NO REQUIRED SPACE AVAILABLE!");
		}
		return customer;
	}

	public Customer park(Customer customer, int row, int column) {
		String ticketCode = UUID.randomUUID().toString();
		Ticket ticket = new Ticket();
		Optional<ParkingSpot> parkingSpotOptional = parkingSpots.stream()
				.filter(x -> x.getPositionX() == row && x.getPositionY() == column).findAny();
		if (parkingSpotOptional.isEmpty()) {
			System.out.println("NO SPACE FOUND IN THE ENTERED POSITION!");
			return customer;
		}
		ParkingSpot p = parkingSpotOptional.get();
		if (!p.isOccupied() && p.getSize().equals(customer.getVehicle().getVehicleSize())
				&& p.getType().equals(customer.getPreferredParkingType())) {
			p.setOccupied(true);
			p.setTicketCode(ticketCode);
			ticket.setTicketCode(ticketCode);
			ticket.setPosition(p.getPositionX(), p.getPositionY());
			customer.setTicket(ticket);
		} else {
			System.out.println("THIS SPACE IS NOT COMPATIBLE!");
		}

		return customer;
	}

	public Customer fetchVehicle(Customer customer) {
		if (customer.getTicket() == null) {
			System.out.println("No Ticket Found!");
			return customer;
		}
		Optional<ParkingSpot> parkingSpotOptional = parkingSpots.stream()
				.filter(p -> p.getTicketCode().equals(customer.getTicket().getTicketCode())).findAny();
		if (parkingSpotOptional.isEmpty()) {
			System.out.println("CAR NOT PRESENT!");
		} else {
			ParkingSpot parkingSpot = parkingSpotOptional.get();
			parkingSpot.setOccupied(false);
			parkingSpot.setTicketCode("");
			customer.setTicket(null);
			System.out.println("Your vehicle has been successfully fetched");
		}
		return customer;
	}

}
