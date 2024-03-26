package application;

// Very basic implementation, need to consider intergration with the Controller + Customer, alongside access to excel sheet.
//
import java.time.LocalDate;

public class HotelRoom {
        
	private String HotelType;
	private int HotelCost;
	private int RoomID;
	private LocalDate CheckIn;
	private LocalDate CheckOut;
	
	public HotelRoom() {
		HotelType = "";
		HotelCost = 0;
		RoomID = 0;
	}
	
	public HotelRoom(String Type, int Cost, int RoomID) {
		
		
	}

	public String getHotelType() {
		return HotelType;
	}

	public void setHotelType(String hotelType) {
		HotelType = hotelType;
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
	
	public void SetCheckOutDate(LocalDate CheckOutTime) {
		CheckOut = CheckOutTime;
	}
	
	
}
