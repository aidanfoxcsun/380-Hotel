package application;

// Very basic implementation, need to consider integration with the Controller + Customer, alongside access to excel sheet.
//
import java.time.LocalDate;

/**
 * 
 * @author Aidan Fox
 * Description: HotelRoom class is used to represent individual rooms that will get stored in the Rooms Excel Spreadsheet. Stores information such as room number, cost, type.
 */
public class HotelRoom {
    private final int maxDates = 64;
    
	private RoomTypes RoomType; // Changed from string to new RoomTypes enum. 
	private int HotelCost;
	private int RoomID;
	
	private LocalDate CheckIn;
	private LocalDate CheckOut;
	
	// Excel related attributes
	private int row; // Location of the Room object in the excel sheet. Useful to perform operations without search.
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Empty HotelRoom constructor. Hotel cost and room number initialized to 0, type initialized to the placeholder type.
	 */
	public HotelRoom() {
		RoomType = RoomTypes.__PLACEHOLDER__;
		HotelCost = 0;
		RoomID = 0;
		
	}
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Explicit HotelRoom constructor.
	 * @param type
	 * @param Cost
	 * @param RoomID
	 */
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
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Adds the current room to the Rooms Excel Spreadsheet. Duplicates are not stored, just serve as a new reference to the spot in the excel sheet.
	 */
	public void AddRoom() {  // Private because this should never be handled by the client. Main function creates the room object and the object stores itself in the excel sheet.
		Excel excel = new Excel();
		int i = 1; //  space for the column titles
		while(excel.getCell("Rooms", i, 0) != null) {
			if(excel.getCell("Rooms", i, 0).getNumericCellValue() == RoomID) {
				this.row = i; // stores the location
				return;
			}
			i++; 
		}
		this.row = i;
		excel.CreateCell("Rooms", this.row, 0, RoomID + ""); // Room ID stored as a string for lookup in the spreadsheet.
	}
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Adds a new pair of reservation dates to the room in the Rooms Excel Spreadsheet. Does not check for overlapping dates.
	 * @param checkIn
	 * @param checkOut
	 */
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
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Helper function to compare chronology of two dates, One formatted as a LocalDate, the other as a String representation of a LocalDate.
	 * @param newDate
	 * @param storedDate
	 * @return
	 */
	protected int compareDate(LocalDate newDate, String storedDate) {
		LocalDate date;
		if(storedDate.length() < 10) { return 0; }
		// Parse the LocalDate object from the string.
		int year = Integer.parseInt(storedDate.substring(0, 4));
		int month = Integer.parseInt(storedDate.substring(5,7));
		int day = Integer.parseInt(storedDate.substring(8,10));
		date = LocalDate.of(year, month, day);
		
		return newDate.compareTo(date);
	}
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Similar to a push method in a Stack. Shifts all values from a given index one to the right. Does not insert a value.
	 * @param e
	 * @param index
	 */
	private void push(Excel e, int index) {
		String buffer[] = new String[maxDates];
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
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Removes the most "recent" (first in the list) reservation pair for a given HotelRoom in the Rooms Spreadsheet. Similar to a pop method for a stack.
	 */
	public void Checkout() {
		String buffer[] = new String[maxDates]; // for storing the checkIn checkOut dates to be shifted. 30 is an arbitrary number, may need to be increased.
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
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Cancels a reservation when given the check in date. Do not use the check out date. Similar to the Checkout method, but finds an index to "pop" from.
	 * @param reservation
	 */
	public void Cancel(LocalDate reservation) {
		String buffer[] = new String[maxDates];
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
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Given the Room Number(ID) as a String, finds the room in the Excel spreadsheet, and if it exists, returns a HotelRoom object with related ID, cost and type information.
	 * @param ID
	 * @return HotelRoom
	 */
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
					cost = 110;
					type = RoomTypes.SINGLE;
					break;
				case 2:
					cost = 190;
					type = RoomTypes.DOUBLE;
					break;
				case 3:
					cost = 225;
					type = RoomTypes.KING;
					break;
				case 4:
					cost = 310;
					type = RoomTypes.SUITE;
					break;
				default:
					cost = -1;
					break;
				}
				
				HotelRoom r = new HotelRoom(type, cost, roomID);
				r.SetRow(i);
				return r;
			}
			i++; 
		}
		return null;
	}
	
	
	
}