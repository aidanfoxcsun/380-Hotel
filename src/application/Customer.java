package application;

import java.time.LocalDate;

public class Customer {
    private String CustomerFirstName;
    private String CustomerLastName;
    private String CustomerEmail;
    private String CustomerPhoneNumber;
    private HotelRoom CustomerHotelRoom;
    private String CustomerCreditCard;
    
    public int row;
    
    
    public Customer() {
    	setCustomerFirstName(null);
    	setCustomerLastName(null);
    	setCustomerEmail(null);
    	setCustomerPhoneNumber(null);
    	setCustomerHotelRoom(null);
    }
    
    public Customer(String FName, String LName, String Email, String PhoneNum, String HotelRoom, String CreditCard) {
    	setCustomerFirstName(FName);
    	setCustomerLastName(LName);
    	setCustomerEmail(Email);
    	setCustomerPhoneNumber(PhoneNum);
    	setCustomerHotelRoom(HotelRoom);
    	setCustomerCreditCard(CreditCard); //Note: This presumes we use Luhn's algorithm beforehand for validation.
    }
    
 
	public String getCustomerLastName() {
		return CustomerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		CustomerLastName = customerLastName;
	}

	public String getCustomerFirstName() {
		return CustomerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		CustomerFirstName = customerFirstName;
	}

	public HotelRoom getCustomerHotelRoom() {
		return CustomerHotelRoom;
	}

	public void setCustomerHotelRoom(String customerHotelRoom) {
		CustomerHotelRoom = HotelRoom.FindRoom(customerHotelRoom);
		if(CustomerHotelRoom == null) {
			System.err.println("ROOM NOT FOUND: " + customerHotelRoom);
		}
	}

	public String getCustomerPhoneNumber() {
		return CustomerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		CustomerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerEmail() {
		return CustomerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}

	public String getCustomerCreditCard() {
		return CustomerCreditCard;
	}

	public void setCustomerCreditCard(String customerCreditCard) {
		CustomerCreditCard = customerCreditCard;
	}
	
	public void UpdateExcel() {
		Excel e = new Excel();
		int i = 1;
		while(e.getCell("Victims", i, 0) != null) {
			if(e.getCell("Victims", i, 0).getStringCellValue().equals(CustomerFirstName)) {
				this.row = i; // stores the location
				return;
			}
			i++; 
		}
		this.row = i;
		e.CreateCell("Victims", this.row, 0, CustomerFirstName);
		e.CreateCell("Victims", this.row, 1, CustomerLastName); 
		e.CreateCell("Victims", this.row, 2, CustomerEmail);
		e.CreateCell("Victims", this.row, 3, CustomerPhoneNumber);
		e.CreateCell("Victims", this.row, 6, CustomerCreditCard);
		this.UpdateRoom(this.CustomerHotelRoom, null, null);
	}
	
	public void UpdateRoom(HotelRoom room, String checkIn, String checkOut) {
		Excel e = new Excel();
		e.CreateCell("Victims", this.row, 10, room.getRoomID() + "");
		e.CreateCell("Victims", this.row, 11, room.getHotelType().toString());
		e.CreateCell("Victims", this.row, 12, room.getHotelCost() + "");
		if(checkIn != null)
			e.CreateCell("Victims", this.row, 13, checkIn.toString());
		if(checkOut != null)
			e.CreateCell("Victims", this.row, 14, checkOut.toString());
	}
	
	public void Remove() {
		Excel e = new Excel();
		for(int i = 0; i < 20; i++) {
			if(e.getCell("Victims", row, i) != null && e.getCell("Victims", row, i).getStringCellValue() != "") {
				e.DeleteCell("victims", row, i);
			}
		}
	}
	
	public static Customer findCustomer(String lastName, int roomID) {
		Excel e = new Excel();
		int i = 1;
		while(e.getCell("Victims", i, 0) != null && e.getCell("Victims", i, 0).getStringCellValue() != "") {
			System.out.println("Checking row: " + i + "; " + e.getCell("Victims", i, 1).getStringCellValue());
			if((e.getCell("Victims", i, 1).getStringCellValue().equals(lastName)) &&
					Integer.parseInt(e.getCell("Victims", i, 10).getStringCellValue()) == roomID) {
				System.out.println("Customer found!");
				return new Customer(e.getCell("Victims", i, 0).getStringCellValue(),
						e.getCell("Victims", i, 1).getStringCellValue(),
						e.getCell("Victims", i, 2).getStringCellValue(),
						e.getCell("Victims", i, 3).getStringCellValue(),
						e.getCell("Victims", i, 10).getStringCellValue(),
						e.getCell("Victims", i, 6).getStringCellValue());
			}
			i++;
		}
		
		System.out.println("No customer found!");
		return null;
	}
	
	@Override
	public String toString() {
		String card = "****";
		card += CustomerCreditCard.substring(CustomerCreditCard.length() - 4, CustomerCreditCard.length());
		return "[ " + CustomerFirstName + " " + CustomerLastName + ", EMAIL: " + CustomerEmail + ", PHONE: " + CustomerPhoneNumber + ", CARD: " + card  + ", ROOM: " + CustomerHotelRoom + " ]";
	}
    
}
