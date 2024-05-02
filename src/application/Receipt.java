package application;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.util.Properties;

public class Receipt {
   
	 // attributes needed for the session
	 final private String username = "phantominnmanage@gmail.com";
	 final private String password = "ivlk skpj raiq yshv";
	 private Customer CustGrabber;
	 private HotelRoom HotelGrabber;
	 
	 Receipt(Customer customer, HotelRoom hotelroom){
		 CustGrabber = customer;
		 HotelGrabber = hotelroom;
	 }
	
   public void CallEmail() {
   Properties prop = new Properties();
	prop.put("mail.smtp.host", "smtp.gmail.com");
	  prop.put("mail.smtp.port", "465");
      prop.put("mail.smtp.auth", "true");
      prop.put("mail.smtp.socketFactory.port", "465");
      prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      
      
      Session session = Session.getInstance(prop,
              new jakarta.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(username, password);
                  }
              });
      
      try {
    	   Message message = new MimeMessage(session);
    	   message.setFrom(new InternetAddress(username));
    	   message.setRecipient(Message.RecipientType.TO, new InternetAddress(CustGrabber.getCustomerEmail()));
    	   
    	   message.setSubject("Phantom Inn Booking Receipt.");
    	   // Actually create the ReceiptMessage.
    	   // I'm using a bunch of concat methods so I don't need to create a single, monstrously large string and instead write it in smaller chunks.
    	   String ReceiptMessage = "Thank you for booking a stay at the Phantom Inn! Your Room Info and Receipt are down below.";
    	   ReceiptMessage = ReceiptMessage.concat("\n\n");
    	   ReceiptMessage = ReceiptMessage.concat("================================================================================");
    	   ReceiptMessage = ReceiptMessage.concat("\nRoom ID: " + HotelGrabber.getRoomID());
    	   ReceiptMessage = ReceiptMessage.concat("\n\n");
    	   ReceiptMessage = ReceiptMessage.concat(" Check In Date: " + HotelGrabber.GetCheckInDate());
    	   ReceiptMessage = ReceiptMessage.concat("\n Check Out Date: " + HotelGrabber.GetCheckOutDate());
    	   ReceiptMessage = ReceiptMessage.concat("\n\n");
    	   ReceiptMessage = ReceiptMessage.concat("Cost of Stay: $" + HotelGrabber.getHotelCost() + "  every night.");
    	   ReceiptMessage = ReceiptMessage.concat("\n\n Name on Card: " + CustGrabber.getCustomerLastName());
    	   
    	   ReceiptMessage = ReceiptMessage.concat("\n\n" + "================================================================================");
    	   ReceiptMessage = ReceiptMessage.concat("\n\n If you have any questions about your reservation, please send an email to phantominnmanage@gmail.com");
    	   
    	   message.setText(ReceiptMessage);
    	   
    	   Transport.send(message);
    	   
    	   System.out.print("Message has been sent");
    	   
      
   }
      catch(MessagingException e) {
    	  e.printStackTrace();
      
      }
   }
   
   public void CancelReceipt() {
	   
	   Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		  prop.put("mail.smtp.port", "465");
	      prop.put("mail.smtp.auth", "true");
	      prop.put("mail.smtp.socketFactory.port", "465");
	      prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	      
	      
	      Session session = Session.getInstance(prop,
	              new jakarta.mail.Authenticator() {
	                  protected PasswordAuthentication getPasswordAuthentication() {
	                      return new PasswordAuthentication(username, password);
	                  }
	              });
	      
	      try {
	    	   Message message = new MimeMessage(session);
	    	   message.setFrom(new InternetAddress(username));
	    	   message.setRecipient(Message.RecipientType.TO, new InternetAddress(CustGrabber.getCustomerEmail()));
	    	   
	    	   message.setSubject("Phantom Inn Booking Receipt.");
	    	
	    	   message.setText("This email has been sent to confirm the cancelation of your stay at the Phantom Inn \n We hope to see you again soon");
	    	   
	    	   Transport.send(message);
	    	   
	    	   System.out.print("Message has been sent");
	    	   
	      
	   }
	      catch(MessagingException e) {
	    	  e.printStackTrace();
	      
	      }
	       
   }
     //Same as above, but it sends to the management email instead.
   public void SendToManager(String TransactionType) {
	
		   
		   Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			  prop.put("mail.smtp.port", "465");
		      prop.put("mail.smtp.auth", "true");
		      prop.put("mail.smtp.socketFactory.port", "465");
		      prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		      
		      
		      Session session = Session.getInstance(prop,
		              new jakarta.mail.Authenticator() {
		                  protected PasswordAuthentication getPasswordAuthentication() {
		                      return new PasswordAuthentication(username, password);
		                  }
		              });
		      
		      try {
		    	   Message message = new MimeMessage(session);
		    	   message.setFrom(new InternetAddress(username));
		    	   message.setRecipient(Message.RecipientType.TO, new InternetAddress(username));
		    	   
		    	   message.setSubject("Phantom Inn record of transaction:");
		    	
		    	   String bodyMessage = "Transaction type: " + TransactionType;
		    	   bodyMessage.concat("\n\n Name of Customer: " + CustGrabber.getCustomerFirstName() + " " + CustGrabber.getCustomerLastName());
		    	   LocalDate now  = LocalDate.now();
		    	   bodyMessage.concat("\n\n Time of Transaction:" + now );
		    	   bodyMessage.concat("\n\n Room Type: " + HotelGrabber.getHotelType() );
		    	   message.setText(bodyMessage);
		    	   
		    	  
		    	   
		    	   Transport.send(message);
		    	   
		    	   System.out.print("Message has been sent");
		   }
		      catch(MessagingException e) {
		    	  e.printStackTrace();
		     }
		  }
}
