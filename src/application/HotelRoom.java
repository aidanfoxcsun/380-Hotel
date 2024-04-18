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
		
	}
	
	public HotelRoom(RoomTypes type, int Cost, int RoomID) {
		RoomType = type;
		HotelCost = Cost;
		this.RoomID = RoomID;
		
	}
	
	public int GetRow() {
		return row;
	}
	
	public void SetRow(int r) {
		row = r;
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
	
	@Override
	public String toString() {
		return RoomID + "";
	}
	
	// Excel stuff
	
	// Adds the current room to the excel sheet
	// If the room ID already exists in the spreadsheet, it will not be overridden,
	// but this object will store its location to perform other functions on it.
	public void AddRoom() {  // Private because this should never be handled by the client. Main function creates the room object and the object stores itself in the excel sheet.
		Excel excel = new Excel();
		int i = 1; //  space for the column titles
		while(excel.getCell("Rooms", i, 0) != null) {
			if(excel.getCell("Rooms", i, 0).getNumericCellValue() == RoomID) { // RoomID transformed into a string. May change this.
				// System.out.println("Duplicate Room IDs will not be stored!"); // Avoids duplicating rooms or accidentally overriding other ones.
				this.row = i; // stores the location
				return;
			}
			i++; 
		}
		this.row = i;
		excel.CreateCell("Rooms", this.row, 0, RoomID + ""); // Room ID stored as a string for lookup in the spreadsheet.
	}
	
	// Elsewhere there should be a check to ensure that CheckIn, Checkout times do not overlap. This should be handled by the Room selection controller, not the Room itself. 
	public void Reserve(LocalDate checkIn, LocalDate checkOut) {
		Excel excel = new Excel();
		int i = 1; // offset from room id
		while(excel.getCell("Rooms", row, i) != null && excel.getCell("Rooms", row, i).getStringCellValue().strip() != "") { 
			if(compareDate(checkIn, excel.getCell("Rooms", row, i).getStringCellValue()) < 0) { // This doesn't check if the stored date is overlapping, 
																										   // because this should be handled before this method is called
				push(excel, i);
				break;
			}
			i+= 2; 
		} // As to not override existing reservation dates.
		excel.CreateCell("Rooms", row, i, checkIn.toString()); // Just stores the Check in, check out dates as a pair.
		excel.CreateCell("Rooms", row, i + 1, checkOut.toString());
	}
	
	/*
	 *  ########### Helper Functions #######################
	 */
	//                        0123456789
	// DATES are formatted as YYYY-MM-DD
	protected int compareDate(LocalDate newDate, String storedDate) {
		LocalDate date;
		if(storedDate.length() < 10) { return 0; }
		int year = Integer.parseInt(storedDate.substring(0, 4));
		int month = Integer.parseInt(storedDate.substring(5,7));
		int day = Integer.parseInt(storedDate.substring(8,10));
		
		//System.out.println(year + " " + month + " " + day);
		date = LocalDate.of(year, month, day);
		
		return newDate.compareTo(date);
	}
	
	// This essentially just shifts all entries to the right in this row from the given index, making room for a new Date
	private void push(Excel e, int index) {
		String buffer[] = new String[30];
		int i = index;
		while(e.getCell("Rooms", row, i) != null && e.getCell("Rooms", row, i).getStringCellValue().strip() != "") {
			System.out.println("Cell: " + i + ", Contents: " + e.getCell("Rooms", row, i));
			buffer[i - index] = e.getCell("Rooms", row, i).getStringCellValue();
			e.DeleteCell("Rooms", row, i); // deletes the cell once its contents were stored in the buffer.
			i++;
		}
		
		for(int j = 0; j < buffer.length; j++) {
			e.CreateCell("Rooms", row, j + 2 + index, buffer[j]);;
		}
	}
	
	/*
	 * ############## End Helper Functions #####################
	 */
	
	// In the future this may return something other than void, or send an event to a Manager or customer object.
	public void Checkout() {
		String buffer[] = new String[30]; // for storing the checkIn checkOut dates to be shifted. 30 is an arbitrary number, may need to be increased.
										  // I doubt this is a particularly efficient method, but it seems to work fine.
		Excel excel = new Excel();
		if(excel.getCell("Rooms", row, 2) == null || excel.getCell("Rooms", row, 2).getStringCellValue() == "") {
			System.err.println("ERROR: No reserved dates for this Room!"); // Logs as an error, may switch this to a normal log in the future.
			return;
		}
		excel.DeleteCell("Rooms", row, 1); // Delete the first pair of reservation dates, since a checkOut after the closest dates doesn't make sense.
		excel.DeleteCell("Rooms", row, 2); // Deleting other cells would be used for canceling a reservation.
		
		int i = 0;
		while(excel.getCell("Rooms", row, i + 3) != null && excel.getCell("Rooms", row, i + 3).getStringCellValue() != "") { // writing all the reservation dates into the buffer.
			buffer[i] = excel.getCell("Rooms", row, i+3).getStringCellValue();
			excel.DeleteCell("Rooms", row, i + 3); // deletes the cell once its contents were stored in the buffer.
			i++;
		}
		
		for(int j = 0; j < buffer.length; j++) {
			excel.CreateCell("Rooms", row, j+1, buffer[j]); // writing all the contents of that buffer back into the row, effectively shifting everything into place.
		}
	}
	
	
	// As of 4/10/2024: This WILL break if you input the checkout date instead of the checkIn date, so please make sure
	// you are using the correct date.
	// Easy fix may be to pass a date pair and make sure the pair exists; may do this in the future.
	public void Cancel(LocalDate reservation) {
		String buffer[] = new String[30];
		Excel e = new Excel();
		int i = 1;
		int offset = 0;
		boolean found = false;
		while(e.getCell("Rooms", row, i) != null && e.getCell("Rooms", row, i).getStringCellValue().strip() != "") {
			if(compareDate(reservation, e.getCell("Rooms", row, i).getStringCellValue()) == 0) {
				found = true;
				offset = i;
				break;
			}
			i++;
		}
		
		if(!found) {return;}
		
		e.DeleteCell("Rooms", row, i); // Delete the first pair of reservation dates, since a checkOut after the closest dates doesn't make sense.
		e.DeleteCell("Rooms", row, i); // Deleting other cells would be used for canceling a reservation.
		
		while(e.getCell("Rooms", row, i + 2) != null) { // writing all the reservation dates into the buffer.
			buffer[i - offset] = e.getCell("Rooms", row, i+2).getStringCellValue();
			e.DeleteCell("Rooms", row, i + 2); // deletes the cell once its contents were stored in the buffer.
			i++;
		}
		
		for(int j = offset; j < buffer.length; j++) {
			e.CreateCell("Rooms", row, j, buffer[j - offset]); // writing all the contents of that buffer back into the row, effectively shifting everything into place.
		}
	}
	
	
	// Static
	public static HotelRoom FindRoom(String ID) {
		Excel excel = new Excel();
		int i = 1; //  space for the column titles
		while(excel.getCell("Rooms", i, 0) != null) {
			if(excel.getCell("Rooms", i, 0).getNumericCellValue() == Integer.parseInt(ID)) {
				int roomID = (int)excel.getCell("Rooms", i, 0).getNumericCellValue();
				int typeid = roomID / 100;
				int cost = 0;
				RoomTypes type = RoomTypes.__PLACEHOLDER__;
				switch(typeid) { // PLACEHOLDERS!!! CHANGE IN FUTURE
				case 1:
					cost = 100;
					type = RoomTypes.SINGLE;
					break;
				case 2:
					cost = 200;
					type = RoomTypes.DOUBLE;
					break;
				case 3:
					cost = 300;
					type = RoomTypes.KING;
					break;
				case 4:
					cost = 400;
					type = RoomTypes.SUITE;
					break;
				default:
					cost = -1;
					break;
				}
				
				return new HotelRoom(type, cost, roomID);
			}
			i++; 
		}
		return null;
	}
	
	
	
}