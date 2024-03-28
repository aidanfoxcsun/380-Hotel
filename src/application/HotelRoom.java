package application;

// Very basic implementation, need to consider integration with the Controller + Customer, alongside access to excel sheet.
//
import java.time.LocalDate;

public class HotelRoom {
        
	private RoomTypes RoomType; // Changed from string to new RoomTypes enum. 
	private int HotelCost;
	private int RoomID;
	private LocalDate CheckIn;  // I don't think these are necessary with the current implementation. Will talk to Sebastian.
	private LocalDate CheckOut; // ^^
	
	// Excel related attributes
	private int row; // Location of the Room object in the excel sheet. Useful to perform operations without search.
	
	public HotelRoom() {
		RoomType = RoomTypes.__PLACEHOLDER__;
		HotelCost = 0;
		RoomID = 0;
		
		this.AddRoom();
	}
	
	public HotelRoom(RoomTypes type, int Cost, int RoomID) {
		RoomType = type;
		HotelCost = Cost;
		this.RoomID = RoomID;
		
		this.AddRoom();
	}

	public RoomTypes getHotelType() {
		return RoomType;
	}

	public void setHotelType(RoomTypes hotelType) {
		RoomType = hotelType;
	}

	public int getHotelCost() {
		return HotelCost;
	}

	public void setHotelCost(int hotelCost) {
		HotelCost = hotelCost;
	}

	public int getRoomID() {
		return RoomID;
	}

	public void setRoomID(int roomID) {
		RoomID = roomID;
	}
	
	public LocalDate GetCheckInDate() {
		return CheckIn;
	}
	
	
	// May have become deprecated
	public void SetCheckInDate(LocalDate CheckInTime) {
		CheckIn = CheckInTime;
	}
	
	public LocalDate GetCheckOutDate() {
		return CheckOut;
	}
	// -------------------------
	
	public void SetCheckOutDate(LocalDate CheckOutTime) {
		CheckOut = CheckOutTime;
	}
	
	// Excel stuff
	
	// Adds the current room to the excel sheet
	// If the room ID already exists in the spreadsheet, it will not be overridden,
	// but this object will store its location to perform other functions on it.
	private void AddRoom() {  // Private because this should never be handled by the client. Main function creates the room object and the object stores itself in the excel sheet.
		Excel excel = new Excel();
		int i = 1; //  space for the column titles
		while(excel.getCell("Rooms", i, 0) != null) {
			if(excel.getCell("Rooms", i, 0).getStringCellValue().equals(RoomID + "")) { // RoomID transformed into a string. May change this.
				System.out.println("Duplicate Room IDs will not be stored!"); // Avoids duplicating rooms or accidentally overriding other ones.
				this.row = i; // stores the location
				return;
			}
			i++; 
		}
		this.row = i;
		excel.CreateCell("Rooms", this.row, 0, RoomID + ""); // Room ID stored as a string for lookup in the spreadsheet.
	}
	
	// Elsewhere there should be a check to ensure that CheckIn, Checkout times do not overlap. This should be handled by the Room selection controller, not the Room itself.
	// TODO: ensure chronological order is kept. If a new reservation is added that is before the current most recent, everything should be shifted to accommodate. 
	public void Reserve(LocalDate checkIn, LocalDate checkOut) {
		Excel excel = new Excel();
		int i = 1; // offset from room id
		while(excel.getCell("Rooms", row, i) != null) { i++; } // As to not override existing reservation dates.
		excel.CreateCell("Rooms", row, i, checkIn.toString()); // Just stores the Check in, check out dates as a pair.
		excel.CreateCell("Rooms", row, i + 1, checkOut.toString());
	}
	
	// In the future this may return something other than void, or send an event to a Manager or customer object.
	public void Checkout() {
		String buffer[] = new String[30]; // for storing the checkIn checkOut dates to be shifted. 30 is an arbitrary number, may need to be increased.
										  // I doubt this is a particularly efficient method, but it seems to work fine.
		Excel excel = new Excel();
		if(excel.getCell("Rooms", row, 2) == null) {
			System.err.println("ERROR: No reserved dates for this Room!"); // Logs as an error, may switch this to a normal log in the future.
			return;
		}
		excel.DeleteCell("Rooms", row, 1); // Delete the first pair of reservation dates, since a checkOut after the closest dates doesn't make sense.
		excel.DeleteCell("Rooms", row, 2); // Deleting other cells would be used for canceling a reservation.
		
		int i = 0;
		while(excel.getCell("Rooms", row, i + 3) != null) { // writing all the reservation dates into the buffer.
			buffer[i] = excel.getCell("Rooms", row, i+3).getStringCellValue();
			excel.DeleteCell("Rooms", row, i + 3); // deletes the cell once its contents were stored in the buffer.
			i++;
		}
		
		for(int j = 0; j < buffer.length; j++) {
			excel.CreateCell("Rooms", row, j+1, buffer[j]); // writing all the contents of that buffer back into the row, effectively shifting everything into place.
		}
	}
	
	// Up next, canceling a reservation, deleting a room.
	
	
}
