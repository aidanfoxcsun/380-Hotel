package application;




public class Customer {
    private String CustomerFirstName;
    private String CustomerLastName;
    private String CustomerEmail;
    private int CustomerPhoneNumber;
    private int CustomerHotelRoom;
    private int CustomerCreditCard;
    
    
    public Customer() {
    	setCustomerFirstName(null);
    	setCustomerLastName(null);
    	setCustomerEmail(null);
    	setCustomerPhoneNumber(0);
    	setCustomerHotelRoom(0);
    }
    
    public Customer(String FName, String LName, String Email, int PhoneNum, int HotelRoom, int CreditCard) {
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

	public int getCustomerHotelRoom() {
		return CustomerHotelRoom;
	}

	public void setCustomerHotelRoom(int customerHotelRoom) {
		CustomerHotelRoom = customerHotelRoom;
	}

	public int getCustomerPhoneNumber() {
		return CustomerPhoneNumber;
	}

	public void setCustomerPhoneNumber(int customerPhoneNumber) {
		CustomerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerEmail() {
		return CustomerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		CustomerEmail = customerEmail;
	}

	public int getCustomerCreditCard() {
		return CustomerCreditCard;
	}

	public void setCustomerCreditCard(int customerCreditCard) {
		CustomerCreditCard = customerCreditCard;
	}
    
    
    
    
}
