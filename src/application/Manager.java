package application;

public class Manager {

	 //Purpose of this class is to send emails to both a manager and whatever the customer is.
	   private String ManagerEmail, CustomerEmail;
	
	
	     public Manager() {
	    	 ManagerEmail = "PhantomInnManagement@Gmail.com";
	    	 
	     }
	     
	     public Manager(String GivenCustomerEmail) {
	    	 CustomerEmail = GivenCustomerEmail;
	     }
}
