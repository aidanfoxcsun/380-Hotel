package application;

public class Room {
	private int roomNumber;
	private RoomTypes type;
	
	public Room(int number, RoomTypes type) {
		this.roomNumber = number;
		this.type = type;
		
		// excel.write(roomNumber to column A)
		// ^ This should be in numerical order down the column.
	}
	
	public boolean isAvailable(/* Date checkIn, Date checkOut */) {
		// Query the Excel Database for Rooms, check the current room(by room number)
		// Check each pair of check in and check out dates, to see if the requested date falls inside one of the pairs, or not
		// return true if no overlap, false otherwise.
		return true;
	}
	
	public void CheckIn(/* Date checkIn, Date checkOut */) {
		if(isAvailable(/* checkIn, checkOut */)) {
			// Update the Excel spreadsheet to contain the new pair of checkIn - checkOut dates at the CORRECT CHRONOLOGICAL location.
			// TODO: Maybe have the isAvailable method set some flag telling the class which cell to insert at? reduce overhead from a double linear search
		} else {
			System.err.print("ERROR: Requested date is not available!");
		}
	}
	
	public void CheckOut() {
		// Should "Pop" the most recent Date pair from the excel sheet, and move all other dates to fit.
	}
	
	public void CancelReservation(/* Date checkIn */) {
		// removes the Date pair with appropriate check in date.
	}
}
