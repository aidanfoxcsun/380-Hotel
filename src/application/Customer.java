package application;

import java.time.LocalDate;

/**
 * 
 * @author Aidan Fox
 * Date: 4/20/24
 * Description: Class representing a Customer. Contains all the necessary information such as name, email, phone number, payment info as fields. 
 * 				Contains methods for modifying and reading from the Excel Sheet used as the central database
 *
 */
public class Customer {
    private String CustomerFirstName;
    private String CustomerLastName;
    private String CustomerEmail;
    private String CustomerPhoneNumber;
    private HotelRoom CustomerHotelRoom;
    private String CustomerCreditCard;
    private String CVCCode;
    private String Country; 
    private String ZipCode;
    private LocalDate CustomerCheckIn;
    private LocalDate CustomerCheckOut;
    private String EXPDate;
    private String CreditCardFirstName;
    private String CreditCardLastName;
    
    public int row;
    
    /**
     * Author: Aidan Fox
     * Date: 4/20/24
     * Description: Empty Customer constructor. Initializes all fileds to null.
     */
    public Customer() {
    	setCustomerFirstName(null);
    	setCustomerLastName(null);
    	setCustomerEmail(null);
    	setCustomerPhoneNumber(null);
    	
    }
    
    /**
     * Author: Aidan Fox
     * Date: 4/20/24
     * Description: Explicit Customer constructor.
     * @param FName
     * @param LName
     * @param Email
     * @param PhoneNum
     * @param HotelRoom
     * @param CreditCard
     */
    public Customer(String FName, String LName, String Email, String PhoneNum, String HotelRoom, String CreditCard) {
    	setCustomerFirstName(FName);
    	setCustomerLastName(LName);
    	setCustomerEmail(Email);
    	setCustomerPhoneNumber(PhoneNum);
    	setCustomerHotelRoom(HotelRoom);
    	setCustomerCreditCard(CreditCard); //Note: This presumes we use Luhn's algorithm beforehand for validation.
    }
    
    // Getters and Setter methods
 
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

	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Sets the current Hotel Room associated with a the Customer object. Note: this takes the HotelRoom number/ID as a String, and finds the relevant HotelRoom Object using a helper function.
	 * @param customerHotelRoom
	 */
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
	
	public String getCVCCode() {
		return CVCCode;
	}

	public void setCVCCode(String cVCCode) {
		CVCCode = cVCCode;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	public LocalDate getCustomerCheckIn() {
		return CustomerCheckIn;
	}

	public void setCustomerCheckIn(LocalDate customerCheckIn) {
		CustomerCheckIn = customerCheckIn;
	}

	public LocalDate getCustomerCheckOut() {
		return CustomerCheckOut;
	}

	public void setCustomerCheckOut(LocalDate customerCheckOut) {
		CustomerCheckOut = customerCheckOut;
	}

	public String getEXPDate() {
		return EXPDate;
	}

	public void setEXPDate(String eXPDate) {
		EXPDate = eXPDate;
	}

	public String getCreditCardFirstName() {
		return CreditCardFirstName;
	}

	public void setCreditCardFirstName(String creditCardFirstName) {
		CreditCardFirstName = creditCardFirstName;
	}

	public String getCreditCardLastName() {
		return CreditCardLastName;
	}

	public void setCreditCardLastName(String creditCardLastName) {
		CreditCardLastName = creditCardLastName;
	}
	
	// End Getters and Setters
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Updates the Customers Excel spreadsheet to include the current Customer object's relevant information and data.
	 */
	public void UpdateExcel() {
		Excel e = new Excel();
		int i = 1;
		while(e.getCell("Victims", i, 0) != null && !e.getCell("Victims", i, 0).getStringCellValue().equals("")) {
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
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Adds given HotelRoom to the Customers Excel spreadsheet, with appropriate Check In and Check Out dates.
	 * @param room
	 * @param checkIn
	 * @param checkOut
	 */
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
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Removes the Customer object from the Customers Excel Spreadsheet.
	 */
	public void Remove() {
		Excel e = new Excel();
		for(int i = 0; i < 20; i++) {
			if(e.getCell("Victims", row, i) != null && e.getCell("Victims", row, i).getStringCellValue() != "") {
				e.DeleteCell("victims", row, i);
			}
		}
	}
	
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Finds a customer in the Customer Excel spreadsheet, and returns a Customer object with appropriate related information.
	 * @param lastName
	 * @param roomID
	 * @return Customer Object
	 */
	public static Customer findCustomer(String lastName, int roomID) {
		Excel e = new Excel();
		int i = 1;
		while(e.getCell("Victims", i, 0) != null && e.getCell("Victims", i, 0).getStringCellValue() != "") {
			System.out.println("Checking row: " + i + "; " + e.getCell("Victims", i, 1).getStringCellValue());
			if((e.getCell("Victims", i, 1).getStringCellValue().equals(lastName)) &&
					Integer.parseInt(e.getCell("Victims", i, 10).getStringCellValue()) == roomID) {
				System.out.println("Customer found!");
				Customer c = new Customer(e.getCell("Victims", i, 0).getStringCellValue(),
						e.getCell("Victims", i, 1).getStringCellValue(),
						e.getCell("Victims", i, 2).getStringCellValue(),
						e.getCell("Victims", i, 3).getStringCellValue(),
						e.getCell("Victims", i, 10).getStringCellValue(),
						e.getCell("Victims", i, 6).getStringCellValue());
				c.row = i;
				return c;
			}
			i++;
		}
		
		System.out.println("No customer found!");
		return null;
	}
	

	@Override
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: toString method for Customer objects. Displays all relevant information. Card information is hidden, except for the last 4 digits.
	 */
	public String toString() {
		String card = "****";
		card += CustomerCreditCard.substring(CustomerCreditCard.length() - 4, CustomerCreditCard.length());
		return "[ " + CustomerFirstName + " " + CustomerLastName + ", EMAIL: " + CustomerEmail + ", PHONE: " + CustomerPhoneNumber + ", CARD: " + card  + ", ROOM: " + CustomerHotelRoom + " ]";
	}
    
}
