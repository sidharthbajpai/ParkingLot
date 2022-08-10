package com.parkinglot.main;

import java.util.Scanner;

public class ParkingService {

	private static ParkingLot parkingLot;
	private static Scanner scan;

	public static void main(String[] args) {
		System.out.println("Welcome to the parking lot! Let's start with setting up the parking spots!");
		System.out.println("Select the number of Regular Spots");
		scan = new Scanner(System.in);
		int regularSpots = scan.nextInt();
		System.out.println("Select the number of Handicapped Spots");
		int handicappedSpots = scan.nextInt();
		System.out.println("Select the number of Compact Spots");
		int compactSpots = scan.nextInt();

		parkingLot = new ParkingLot(regularSpots, handicappedSpots, compactSpots);
		selection();

	}

	private static void selection() {
		parkingLot.print();
		while (true) {
			System.out.println("Type the number below to perform the assosiated action" + "\n1 - Park your vehicle"
					+ "\n2 - Park your vehcile through a velvet" + "\n3 - Fetch your vehicle from the parking lot"
					+ "\n4 - Exit parking lot");
			int choice = scan.nextInt();
			if (choice == 1) {
				parkVehicleWithCoordinates();
			} else if (choice == 2) {
				parkVehicleThroughVelvet();
			} else if (choice == 3) {
				fetchVehicle();
			} else if (choice == 4) {
				System.out.println("Thank you for visiting! Have a great day!");
				return;
			} else {
				System.out.println("Please enter write choice");
			}
			parkingLot.print();
		}
		
	}

	private static Customer createCustomer() {
		System.out
				.println("Select the adequate parking type!" + "\n1 - regular" + "\n2 - handicapped" + "\n3 - compact");
		int parkingTypeChoice = scan.nextInt();
		String preferredParkingType = "";
		boolean isValid = false;
		while (!isValid) {
			if (parkingTypeChoice == 1) {
				preferredParkingType = ParkingSpot.TYPE_REGULAR;
				isValid = true;
			} else if (parkingTypeChoice == 2) {
				preferredParkingType = ParkingSpot.TYPE_HANDICAPPED;
				isValid = true;
			} else if (parkingTypeChoice == 3) {
				preferredParkingType = ParkingSpot.TYPE_COMPACT;
				isValid = true;
			} else {
				System.out.println("Please Enter Valid Choice");
				isValid = false;
			}
		}
		Vehicle vehicle = createVehicle();
		Customer customer = new Customer(vehicle, preferredParkingType);
		return customer;
	}

	private static Vehicle createVehicle() {
		System.out.println("Select your vehicle size" + "\n1 - small\n2 - medium\n3 - large");
		int vehicleChoice = scan.nextInt();
		String vehicleSize = "";
		boolean isValid = false;
		while (!isValid) {
			switch (vehicleChoice) {
			case 1:
				vehicleSize = Vehicle.SIZE_SMALL;
				isValid = true;
				break;
			case 2:
				vehicleSize = Vehicle.SIZE_MEDIUM;
				isValid = true;
				break;
			case 3:
				vehicleSize = Vehicle.SIZE_LARGE;
				isValid = true;
				break;
			default:
				System.out.println("Please Enter Valid Choice");
				isValid = false;
			}
		}
		Vehicle vehicle = new Vehicle(vehicleSize);
		return vehicle;
	}

	private static void parkVehicleWithCoordinates() {
		Customer customer = createCustomer();
		System.out.println("Enter the parking row and column in form (n n) ");
		int row = scan.nextInt();
		int column = scan.nextInt();
		customer = parkingLot.park(customer, row, column);
		if (customer.getTicket() == null) {
			return;
		}
		System.out.println("Your vehicle has been parked on spot - " + customer.getTicket().getPosition()
				+ "\nYour ticket no. is - " + customer.getTicket().getTicketCode()
				+ "\nMake sure to keep a reference while picking up your vehicle");
	}

	private static void parkVehicleThroughVelvet() {
		Customer customer = createCustomer();
		System.out.println("Which gate you are entering through?" + "\n1 - front gate" + "\n2 - back gate");
		int gateChoice = 0;
		boolean enteredFromBackGate = false;
		while (true) {
			gateChoice = scan.nextInt();
			if (gateChoice < 1 && gateChoice > 2) {
				System.out.println("Please enter right choice!");
				continue;
			}
			if (gateChoice == 2) {
				enteredFromBackGate = true;
			}
			break;
		}
		customer = parkingLot.parkThroughVelvet(customer, enteredFromBackGate);
		if(customer.getTicket() == null ) {
			return;
		}
		System.out.println("Your vehicle has been parked on spot - " + customer.getTicket().getPosition()
				+ "\nYour ticket no. is - " + customer.getTicket().getTicketCode()
				+ "\nMake sure to keep a note of it in order to fetch your vehicle");
	}

	private static void fetchVehicle() {
		System.out.println("Please Enter your token code");
		scan.nextLine();
		String tokenCode = scan.nextLine();
		Customer customer = new Customer();
		Ticket ticket = new Ticket();
		ticket.setTicketCode(tokenCode);
		customer.setTicket(ticket);
		parkingLot.fetchVehicle(customer);
	}

}
