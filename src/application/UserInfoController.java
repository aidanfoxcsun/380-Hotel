package application;
import java.io.IOException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.util.HashMap;

public class UserInfoController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	
	
	private String SFirstName, SLastName, SEmail, SNumber, SRoom, SCard;
	private String CardFirstName, CardLastName, CardZipCode, CVC,ExpMonth,ExpYear,CardCountry;
	
	// uncomment if this is going to be used private Button ConfirmButton;
	private HotelRoom HotelRoom = new HotelRoom();
	
	@FXML
	private ToggleGroup Room;
	
	@FXML
	private Label CreditCardError;
	
	@FXML
	private RadioButton SingleButton;
	@FXML
	private RadioButton DoubleButton;
	@FXML
	private RadioButton KingButton;
	@FXML
	private RadioButton SuiteButton;
	
	@FXML
	private DatePicker CheckInDate;
	
	@FXML
	private DatePicker CheckOutDate;
	
	@FXML
	private Label DateChecker;
	
	@FXML
	private Label RoomAvailabilityChecker;

	@FXML 
	private ImageView Image;
	
	@FXML
	private TextField FirstName;
	
	@FXML
	private TextField LastName;
	
	@FXML
	private TextField Email;
	
	@FXML
	private TextField PhoneNumber;
	
	@FXML
	private TextField CFirstName;
	
	@FXML
	private TextField CLastName;
	
	@FXML
	private TextField CreditCardNumber;
	
	@FXML 
	private TextField CVCNumber;
	
	@FXML
	private TextField ExpirationYear;
	
	@FXML
	private TextField ExpirationMonth;
	
	@FXML
	private TextField ZipCode;
	
	@FXML
	private TextField Country;
	
	@FXML
	private Label FirstNameStatus;
	
	@FXML
	private Label LastNameStatus;
	
	@FXML
	private Label PhoneStatus;
	
	@FXML
	private Label EmailStatus;
	@FXML
	private Label CCFirstNameStatus;
	@FXML
	private Label CCLastNameStatus;
	@FXML 
	private Label CCNumStatus;
	@FXML
	private Label CVCStatus;
	@FXML
	private Label EXPMonthStatus;
	@FXML
	private Label EXPYearStatus;
	@FXML
	private Label ZipCodeStatus;
	@FXML
	private Label CountryStatus;
	
	
	

    
	/**
	 * Simply makes customer object and returns it for later bookkeeping.
	 * Author: Sebastian Sunga
	 * Date: 03/28/2024
	 * @return Customer
	 * @throws IOException
	 */
	@FXML
	public Customer confirmRoom() throws IOException {
		SRoom = Integer.toString(HotelRoom.getRoomID());
		Customer cust = new Customer(SFirstName, SLastName, SEmail, SNumber, SRoom, SCard);
		cust.setCountry(CardCountry);
		cust.setCVCCode(CVC);
		cust.setCustomerCheckIn(HotelRoom.GetCheckInDate());
		cust.setCustomerCheckOut(HotelRoom.GetCheckOutDate());	
		cust.setZipCode(CardZipCode);
		
		String ExpDate = ExpMonth.concat("/");
		ExpDate = ExpDate.concat(ExpYear);
		cust.setEXPDate(ExpDate);
		
		return cust;
	}
	
	private boolean ExpMonthValidate(String Month) {
		int TotalDigit = 0;
	   for(int i = 0 ; i < Month.length() ; i++) {
		   TotalDigit += Integer.parseInt(Month.substring(i, i + 1));
		   
	   }
	   if(TotalDigit > 9)
		   return false;
	   else
		   return true;
	}
	
	/**
	 * Parses Expiration month value when User presses enters, clears it when user presses backspace.
	 * Author: Sebastian Sunga
	 * Date: 04/18/2024
	 * @param Event
	 */
	@FXML
	public void ExpMonthCheck(KeyEvent Event) {
		String TempInput = ExpirationMonth.getText();
		  switch(Event.getCode()) {
		  case ENTER:
			  if(TempInput.length() == 2 && ExpMonthValidate(TempInput)) {
				  ExpMonth = TempInput;
				  EXPMonthStatus.setTextFill(Color.color(0, 1, 0));
			      EXPMonthStatus.setText("✓");
			  }
			  else{
				  EXPMonthStatus.setTextFill(Color.color(1, 0, 0));
			      EXPMonthStatus.setText("INVALID");
			  }
			  break;
		  case BACK_SPACE:
			  ExpMonth = "";
			  EXPMonthStatus.setTextFill(Color.color(1, 0, 0));
		      EXPMonthStatus.setText("**");
			  break;
		  default:
			  break;
		  }
	}
	/**
	 * Validates and stores the Expiration Year.
	 * Author: Sebastian Sunga
	 * Date: 04/18/2024
	 * @param Event
	 * @throws IOException
	 */
	@FXML
	 public void EXPYearCheck(KeyEvent Event) throws IOException{
		String TempInput = ExpirationYear.getText();
		  switch(Event.getCode()) {
		  case ENTER:
			  if(TempInput.length() == 2 && NumberVerify(TempInput)) {
				  ExpYear = TempInput;
				  EXPYearStatus.setTextFill(Color.color(0, 1, 0));
				  EXPYearStatus.setText("✓");
			  }
			  else {
				  EXPYearStatus.setTextFill(Color.color(1, 0, 0));
			      EXPYearStatus.setText("INVALID");
			  }
			  break;
		  case BACK_SPACE:
			  ExpYear = "";
			  EXPYearStatus.setTextFill(Color.color(1, 0, 0));
		      EXPYearStatus.setText("**");
			  break;
		  default:
			  break;
		  }
	}
	
	
	/**
	 * Validates and stores the Credit Card's first name
	 * Author: Sebastian Sunga
	 * Date: 04/18/2024
	 * @param Event
	 */
	@FXML 
	public void CFirstnameCheck(KeyEvent Event) {
		   String TempString = CFirstName.getText();
			  switch(Event.getCode()) {
			  case ENTER:
				  
				  if(!TempString.isBlank()) {
				  CardFirstName = TempString;
				  CCFirstNameStatus.setTextFill(Color.color(0, 1, 0));
				  CCFirstNameStatus.setText("✓");
				  }
				  break;
			  case BACK_SPACE:
				  CardFirstName = "";
				  CCFirstNameStatus.setTextFill(Color.color(1, 0, 0));
				  CCFirstNameStatus.setText("**");
				  break;
				  default:
				  // Do nothing. Probably some form of check if it's a valid input. but in the case of First names
				  // We don't need to be very strict in validation. Because if someone enters a wrong name it's kinda their fault.
				  break;
	 		  }
		
	}
	
	/**
	 * Author: Sebastian Sunga
	 * Date: 04/18/2024
	 * Description: validates and handles Credit Card Last Name.
	 * @param Event
	 */
	@FXML
	public void CLastNameCheck(KeyEvent Event) {
		String TempInput = CLastName.getText();
		  switch(Event.getCode()) {
		  case ENTER:
			  if(!TempInput.isBlank()) { 
			  CardLastName = TempInput;
			  CCLastNameStatus.setTextFill(Color.color(0, 1, 0));
			  CCLastNameStatus.setText("✓");
			  }
			  break;
		  case BACK_SPACE:
			  CardLastName = "";
			  CCLastNameStatus.setTextFill(Color.color(1, 0, 0));
			  CCLastNameStatus.setText("**");
			  break;
			  default:
			  // Do nothing. Probably some form of check if it's a valid input. but in the case of First names
			  // We don't need to be very strict in validation. Because if someone enters a wrong name it's kinda their fault.
			  break;
		  }
	}
	/**
	 * Author: Sebastian Sunga
	 * Date: 04/18/2024
	 * Description: Handles the CVC data, validates and stores.
	 * @param Event
	 * @throws IOException
	 */
	
	@FXML
	public void CVCStatusCheck(KeyEvent Event) throws IOException {
		
		String TempInput = CVCNumber.getText();
		  switch(Event.getCode()) {
		  case ENTER:
			  if(TempInput.length() == 3 && NumberVerify(TempInput)) {
				  CVC = TempInput;
				  CVCStatus.setTextFill(Color.color(0, 1, 0));
				  CVCStatus.setText("✓");
			  }
			  else {
				  CVCStatus.setTextFill(Color.color(1, 0, 0));
			      CVCStatus.setText("INVALID");
			  }
			  break;
		  case BACK_SPACE:
			  CVC = "";
			  CVCStatus.setTextFill(Color.color(1, 0, 0));
		      CVCStatus.setText("**");
			  break;
		  default:
			  break;
		  }
	}
	 /**
	  * Author: Sebastian Sunga
	  * Date: 04/18/2024
	  * Description: validates and stores the Zipcode data
	  * @param Event
	  * @throws IOException
	  */
	 @FXML
	public void ZipcodeStatusCheck(KeyEvent Event) throws IOException {
		 String TempInput = ZipCode.getText();
		  switch(Event.getCode()) {
		  case ENTER:
			  if(TempInput.length() == 5 && NumberVerify(TempInput)) {
				  CardZipCode = TempInput;
				  ZipCodeStatus.setTextFill(Color.color(0, 1, 0));
				  ZipCodeStatus.setText("✓");
			  }
			  else {
				  ZipCodeStatus.setTextFill(Color.color(1, 0, 0));
			      ZipCodeStatus.setText("INVALID");
			  }
			  break;
		  case BACK_SPACE:
			  CardZipCode = "";
			  ZipCodeStatus.setTextFill(Color.color(1, 0, 0));
		      ZipCodeStatus.setText("**");
			  break;
		  default:
			  break;
		  }
	}
	 /**
	  * Author: Sebastian Sunga
	  * Date: 04/18/2024
	  * Description: Handles the Country data.
	  * @param Event
	  * @throws IOException
	  */
	 @FXML
	public void CountryStatusCheck(KeyEvent Event) throws IOException {
		   String TempString = Country.getText();
			  switch(Event.getCode()) {
			  case ENTER:
				  if(!TempString.isBlank()) {
				  CardCountry = TempString;
				  CountryStatus.setTextFill(Color.color(0, 1, 0));
				  CountryStatus.setText("✓");
				  }
				  else {
					  CountryStatus.setTextFill(Color.color(1, 0, 0));
					  CountryStatus.setText("INVALID"); 
				  }
				  break;
			  case BACK_SPACE:
				  CardCountry = "";
				  CountryStatus.setTextFill(Color.color(1, 0, 0));
				  CountryStatus.setText("**");
				  break;
				  default:
				  // Do nothing. Probably some form of check if it's a valid input. but in the case of First names
				  // We don't need to be very strict in validation. Because if someone enters a wrong name it's kinda their fault.
				  break;
	 		  }
		
	}
	
	// Sole Purpose of this method is for RoomSelectionController loader to actually pass on the HotelReservation data.
	 /**
	  * Author: Sebastian Sunga
	  * Date: 04/07/2024
	  * Description: Small method to handle and set the hotel room data from RoomSelectionController.
	  * @param Hotel
	  */
	public void HotelGrabber(HotelRoom Hotel) {
		HotelRoom = Hotel;
		// System.out.print("THIS WORKED");
	}
	
	
/**
 * Author: Sebastian Sunga
 * Date: 04/18/2024
 * Description: Handles the First Name data. validates and stores
 * 
 * @param Event
 * @throws IOException
 */
	
	@FXML
	public void FirstNameSetter(KeyEvent Event) throws IOException{
		   String TempString = FirstName.getText();
		  switch(Event.getCode()) {
		  case ENTER:
			  
			  
			  if(!TempString.isBlank()) {
			  SFirstName = FirstName.getText();
			  FirstNameStatus.setTextFill(Color.color(0, 1, 0));
			  FirstNameStatus.setText("✓");
			   
			  }
			  break;
		  case BACK_SPACE:
			  SFirstName = FirstName.getText();
			  FirstNameStatus.setTextFill(Color.color(1, 0, 0));
			  FirstNameStatus.setText("**");
			  
			  break;
			  default:
			  // Do nothing. Probably some form of check if it's a valid input. but in the case of First names
			  // We don't need to be very strict in validation. Because if someone enters a wrong name it's kinda their fault.
			  break;
 		  }
		
		 
	}
	/**
	 * Author: Sebastian Sunga
	 * Date: 04/21/2024
	 * Description: Sets SLastname from user input.
	 * @param Event
	 * @throws IOException
	 */
	
	@FXML
	public void LastNameSetter(KeyEvent Event) throws IOException{
		String TempInput = LastName.getText();
		  switch(Event.getCode()) {
		  case ENTER:
			  
			  
			  if(!TempInput.isBlank()) { 
			  SLastName = LastName.getText();
			  LastNameStatus.setTextFill(Color.color(0, 1, 0));
			  LastNameStatus.setText("✓");
			  
			  }
			  break;
		  case BACK_SPACE:
			  SLastName = "";
			  LastNameStatus.setTextFill(Color.color(1, 0, 0));
			  LastNameStatus.setText("**");
			  
			  break;
			  default:
			  // Do nothing. Probably some form of check if it's a valid input. but in the case of First names
			  // We don't need to be very strict in validation. Because if someone enters a wrong name it's kinda their fault.
			  break;
 		  }
	}
	
	private boolean EmailVerify(String Email) throws IOException{ 
		for(int i = 0 ; i < Email.length() ; i++) {
			
			if(Email.charAt(i) == '@')
					return true;
		}
		return false;
	}
	
	/**
	 * Author: Sebastian Sunga
	 * Date: 04/18/2024
	 * Description: Grabs a valid email from user input.
	 * @param Event
	 * @throws IOException
	 */
	
	@FXML
	public void EmailSetter(KeyEvent Event) throws IOException{
		String TempEmail = Email.getText();
		switch(Event.getCode()) {
		case ENTER:
			if(!TempEmail.isBlank() && EmailVerify(TempEmail)) {
				SEmail = Email.getText();
				EmailStatus.setTextFill(Color.color(0, 1, 0));
				EmailStatus.setText("✓");
			}
			else{
				EmailStatus.setTextFill(Color.color(1, 0, 0));
				EmailStatus.setText("Invalid Email");
			}
			break;
		case BACK_SPACE:
			SEmail = ""; // If they are back spacing, we want to clear for when they actually press enter.
			EmailStatus.setTextFill(Color.color(1, 0, 0));
			EmailStatus.setText("**");
			break;
		 default:
			 break;
		}
		
		
		
	}
	
	
	
	
	/**
	 * Author: Sebastian Sunga
	 * Date: 04/18/2024
	 * Description: Takes a given number(as string), and tests if the character is a digit. false if not, true if all are digits.
	 * @param GivenNumber
	 * @return
	 * @throws IOException
	 */
	private boolean NumberVerify(String GivenNumber) throws IOException{ 
		for(int i = 0 ; i < GivenNumber.length() ; i++) {
			char TestDigit = GivenNumber.charAt(i);
			if(!Character.isDigit(TestDigit))
					return false;
		}
		return true;
	}
	/**
	 * Author: Sebastian Sunga
	 * Date: 04/18/2024
	 * Description: Validates and sets the phone number data.
	 * @param Event
	 * @throws IOException
	 */
	@FXML
	public void PhoneNumSetter(KeyEvent Event) throws IOException{
		  String TempInput = PhoneNumber.getText();
		  switch(Event.getCode()) {
		  case ENTER:
			  System.out.println(TempInput.length());
			  if(!TempInput.isBlank() && TempInput.length() == 10 && NumberVerify(TempInput)) { //Very nasty looking
				   
			         SNumber = TempInput;
			         PhoneStatus.setTextFill(Color.color(0, 1, 0));
			         PhoneStatus.setText("✓");		
			         
			  } // End of If statement
			  else {
				  PhoneStatus.setText("Please enter a valid Phone Number.");
				  SNumber = "";
			  }
			  break;
		  case BACK_SPACE:
			  PhoneStatus.setTextFill(Color.color(1, 0, 0));
			  PhoneStatus.setText("**");
			  
			  break;
			  
			  default:
			  
			  // Do nothing. Probably some form of check if it's a valid input. but in the case of First names
			  // We don't need to be very strict in validation. Because if someone enters a wrong name it's kinda their fault.
			  break;
 		  }
	}
	
	/**
	 * Author: Sebastian Sunga
	 * Date: 04/18/2024 
	 * Description: Validates and stores given CC # data. Uses the CreditCard Checker to validate.
	 * @param Event
	 * @throws IOException
	 */
	
	@FXML
	  public void CreditCardSetter(KeyEvent Event) throws IOException{
		String TempInput = CreditCardNumber.getText();
		
		 switch(Event.getCode()) {
		 case ENTER:
			 if(TempInput.length() == 16 && CreditCardChecker()) {
				 SCard = TempInput;
				 CreditCardError.setTextFill(Color.color(0,1,0));
				 CreditCardError.setText("✓");
			 }
			 else {
				 CreditCardError.setTextFill(Color.color(1, 0, 0));
				 CreditCardError.setText("INVALID");
			 }
			 break;
		 case BACK_SPACE:
			 SCard = "";
			 CreditCardError.setTextFill(Color.color(1, 0, 0));
			 CreditCardError.setText("**");
			 break;
		default:
			 break;
		 }
			
			
		}
	
	/**
	 * Author: Sebastian Sunga
	 * Date: 04/21/2024
	 * Takes given credit card number as a string. converts into a parsable long int, then validates using Luhn's algorithm.
	 * @return true if valid number, false if 
	 * @throws IOException
	 */
	public boolean CreditCardChecker() throws IOException{
		// Figure out credit card, because java doesn't like unsigned longs.
		
		
		
		int[] CardNum = new int[CreditCardNumber.getText().length()];
		int SumOfNumbers = 0;
        for(int i = 0 ; i < CardNum.length; i++) {
        	CardNum[i] = Integer.parseInt(CreditCardNumber.getText().substring(i, i+1));
        }
        
        for(int i = CardNum.length - 2; i >= 0 ; i = i -2) {
        	int TempNum = CardNum[i];
        	TempNum = TempNum * 2;
        	if(TempNum >= 10)
        		TempNum = (TempNum % 10) + 1;
        	CardNum[i] = TempNum;
        }
        
        for(int i = 0 ; i < CardNum.length; i++) {
        	SumOfNumbers += CardNum[i];
        }
        
        if(SumOfNumbers % 10 == 0)
        	return true;
        else
        	return false;

	}
	
	/*
	/ I'm thinking that only the fields that require validation get their own methods.
	 *  Basic fields that doesn't require validation can just be grabbed with the next button.
	 */
	
	
	

	
	
	
	
	
	/**
	 * Descripton: Switch to the Reviewpage view and controller with the data handled here.
	 */
	 
	@FXML
    public void switchToReviewPage(ActionEvent event) throws IOException {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReviewPage.FXML"));
                root = loader.load();
                Customer CustomerData = confirmRoom();
                ReviewPageController ReviewPageController = loader.getController();
                ReviewPageController.InfoGrabber(CustomerData, HotelRoom);		
		
            //root = FXMLLoader.load(getClass().getResource("ReviewPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	
	@FXML
    public void switchToHomePage(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("HomePage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	@FXML
    public void switchToRoomSelectionScreen(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("RoomSelectionPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}