package application;

public class Customer {
    private String CustomerFirstName;
    private String CustomerLastName;
    private String CustomerEmail;
    private String CustomerPhoneNumber;
    private String CustomerHotelRoom;
    private String CustomerCreditCard;
    
    
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
    
    /**
     * Really Stupid Write to Victims Sheet method.
     *  called when user presses the book button.
     */
    public void WriteToDatabase() {
    	Excel excel = new Excel();
    	for(int i = 1; i < 17; i++) {
    		if(excel.isNull(i, 1, 0) && excel.isNull(i, 2, 0)){// iterate until we found an empty row.
    			int cols = 1;
    			excel.CreateCell("Victims", i, cols++, CustomerFirstName);
    			excel.CreateCell("Victims",i,cols++,CustomerLastName);
    			excel.CreateCell("Victims", i, cols++, CustomerEmail);
    			excel.CreateCell("Victims",i,cols++, CustomerPhoneNumber);
    			excel.CreateCell("Victims", i, cols++, CustomerHotelRoom);
    			excel.CreateCell("Victims", i, cols++, CustomerCreditCard);
    		    break;
    		}
    		
    		
    	}
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

	public String getCustomerHotelRoom() {
		return CustomerHotelRoom;
	}

	public void setCustomerHotelRoom(String customerHotelRoom) {
		CustomerHotelRoom = customerHotelRoom;
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
    
}
