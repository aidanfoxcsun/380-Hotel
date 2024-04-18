package application;
import java.io.IOException;
import java.time.LocalDate;

import javafx.application.Application;


public class main{

	public static void main(String[] args) {
		// Test code
		// Uncomment to test excel functionality
		/*
		HotelRoom r1 = new HotelRoom(RoomTypes.SINGLE, 123, 100); // creates three new rooms with different types, costs, and types.
		r1.AddRoom();
		HotelRoom r2 = new HotelRoom(RoomTypes.DOUBLE, 234, 200); // Note the new RoomTypes enum. This will help prevent typos when creating Rooms.
		r2.AddRoom();
		HotelRoom r3 = new HotelRoom(RoomTypes.KING, 500, 300);
		r3.AddRoom();
		HotelRoom r4 = new HotelRoom(RoomTypes.SUITE, 1000, 400);
		r4.AddRoom();
		LocalDate checkIn = LocalDate.of(2024, 1, 10); // creates a set of reservation dates.
		LocalDate checkOut = LocalDate.of(2024, 1, 15);
		r1.Reserve(checkIn, checkOut); // Reserves the dates
		r1.Reserve(LocalDate.of(2024, 2, 20), LocalDate.of(2024, 2, 28)); // Reserves a second set of dates for room 1
		r1.Reserve(LocalDate.of(2024, 1, 20), LocalDate.of(2024, 1, 30));
		r1.Reserve(LocalDate.of(2023, 1, 10), LocalDate.of(2023, 1, 20));
		// r1.Checkout(); // removes the first reservation from the excel sheet. Remove this line to see both reservations in the Excel spreadsheet.
		// Make sure to check and clean up the excel file after each run, because this code WILL continue to add reservation dates.
		/*
		r1.Cancel(LocalDate.of(2024, 1, 10));
		r1.Cancel(LocalDate.of(2024, 2, 20));
		r1.Checkout();
		*/
		// End Test code
		
		
		
		
		Application.launch(MainPageController.class, args);
		Excel obj = new Excel();
		
	}
}