package application;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.util.Properties;

public class Receipt {
   
	 // attributes needed for the session
	 final private String username = "phantominnmanage@gmail.com";
	 final private String password = "ivlk skpj raiq yshv"; // App password as google disabled less app secure access for its free smtp.
	 private Customer CustGrabber;
	 private HotelRoom HotelGrabber;
	 
	 Receipt(Customer customer, HotelRoom hotelroom){
		 CustGrabber = customer;
		 HotelGrabber = hotelroom;
	 }
	
   public void SendEmail(EmailTypes EmailType) {
	   // 1 <- Reservation Receipt
	   // 2 <- Cancel Receipt
	   //Set up the properties of the smtp for the session
   Properties prop = new Properties();
	prop.put("mail.smtp.host", "smtp.gmail.com");
	  prop.put("mail.smtp.port", "465");
      prop.put("mail.smtp.auth", "true");
      prop.put("mail.smtp.socketFactory.port", "465"); //
      prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      
      
      Session session = Session.getInstance(prop,
              new jakarta.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() { //evil local scope call that is the only way this works
                      return new PasswordAuthentication(username, password);
                  }
              });
      
       switch(EmailType) {
       case RESERVATION:
    	   try {
    	   Message message = new MimeMessage(session);
    	   message.setFrom(new InternetAddress(username));
    	   message.setRecipient(Message.RecipientType.TO, new InternetAddress(CustGrabber.getCustomerEmail()));
    	   
    	   message.setSubject("Phantom Inn Booking Receipt.");
    	   // Actually create the ReceiptMessage.
    	   // I'm using a bunch of concat methods so I don't need to create a single, monstrously large string and instead write it in smaller chunks.
    	   String ReceiptMessage = "Thank you for booking a stay at the Phantom Inn!\n Your Room Info and Receipt are down below.";
    	   ReceiptMessage = ReceiptMessage.concat("\n\n");
    	   ReceiptMessage = ReceiptMessage.concat("================================================================================");
    	   ReceiptMessage = ReceiptMessage.concat("\nRoom ID: " + HotelGrabber.getRoomID());
    	   ReceiptMessage = ReceiptMessage.concat("\n\n");
    	   ReceiptMessage = ReceiptMessage.concat(" Check In Date: " + HotelGrabber.GetCheckInDate() + ".");
    	   ReceiptMessage = ReceiptMessage.concat("\n Check Out Date: " + HotelGrabber.GetCheckOutDate() + ".");
    	   ReceiptMessage = ReceiptMessage.concat("\n\n");
    	   ReceiptMessage = ReceiptMessage.concat("Cost of Stay: $" + HotelGrabber.getHotelCost() + " every Night.");
    	   ReceiptMessage = ReceiptMessage.concat("\n\n Name on Card: " + CustGrabber.getCreditCardFirstName() + " " + CustGrabber.getCreditCardLastName());
    	   ReceiptMessage = ReceiptMessage.concat("\n\n Card used: ************" + CustGrabber.CardLastFour());
    	   
    	   ReceiptMessage = ReceiptMessage.concat("\n\n" + "================================================================================");
    	   ReceiptMessage = ReceiptMessage.concat("\n\n Please take a screenshot or print out this email for a smoother time at the front desk.");
    	   ReceiptMessage = ReceiptMessage.concat("\n\n If you have any questions about your reservation, please send an email to phantominnmanage@gmail.com");
    	   ReceiptMessage = ReceiptMessage.concat("\n Regards, \n George Stein, Manager at the Phantom Inn Hotel");
    	   
    	   message.setText(ReceiptMessage);
    	   
    	   Transport.send(message);
    	   
    	   SendToManager("Reservation");     
              } //END of try block
      catch(MessagingException e) {
    	  e.printStackTrace();
      
      }
    	   break;
       case CANCELATION:
    	   try {
	    	   Message message = new MimeMessage(session);
	    	   message.setFrom(new InternetAddress(username));
	    	   message.setRecipient(Message.RecipientType.TO, new InternetAddress(CustGrabber.getCustomerEmail()));
	    	   
	    	   message.setSubject("Phantom Inn Booking Receipt.");
	    	
	    	   message.setText("Hello " + CustGrabber.getCustomerFirstName() + " " + CustGrabber.getCustomerLastName() + " this email has been sent to confirm the cancelation of your stay at the Phantom Inn. \n We hope to see you again soon.");
	    	  
	    	   Transport.send(message);
	    	   
	    	   SendToManager("Cancelation");
	    	   
	      
	   }
	      catch(MessagingException e) {
	    	  e.printStackTrace();
	      
	      }
    	   break;
    	   
       default:
    	   break;
       } 	  
   }
   
     //Email that sends to 
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
		    	   LocalDate now  = LocalDate.now();
		    	   String bodyMessage = "Transaction type: ";
		    	 bodyMessage = bodyMessage.concat(TransactionType);
		    	  bodyMessage = bodyMessage.concat("\n\n Name of Customer: " + CustGrabber.getCustomerFirstName() + " " + CustGrabber.getCustomerLastName());
		    	   
		    	  bodyMessage = bodyMessage.concat("\n\n Time of Transaction:" + now );
		    	   bodyMessage = bodyMessage.concat("\n\n Room Type: " + HotelGrabber.getHotelType() );
		    	   message.setText(bodyMessage);
		    	   
		    	  
		    	   
		    	   Transport.send(message);
		    	   
		    	  
		   }
		      catch(MessagingException e) {
		    	  e.printStackTrace();
		     }
		  }
}
