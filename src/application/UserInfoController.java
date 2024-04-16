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
	
	private Label firstName, lastName, email, phoneNumber,  room, card;
	
	private String SFirstName, SLastName, SEmail, SNumber, SRoom, SCard;
	private Button ConfirmButton;
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
	private TextField ExpirationDate;
	
	@FXML
	private TextField ZipCode;
	
	

    
	
	@FXML
	public Customer confirmRoom() throws IOException {
		Customer cust = new Customer(SFirstName, SLastName, SEmail, SNumber, SRoom, SCard);
		return cust;
	}
	
	@FXML
	public void updateUserInfo() {
		SFirstName = firstName.getText();
		SLastName = lastName.getText();
		SEmail = email.getText();
		SNumber = phoneNumber.getText();
		SRoom = room.getText();
		SCard = card.getText();
	}
	
	public void HotelGrabber(HotelRoom Hotel) {
		HotelRoom = Hotel;
		System.out.print("THIS WORKED");
	}
	
	

	@FXML
	public void FirstNameSetter(ActionEvent Event) throws IOException{
		SFirstName = FirstName.getText();
		System.out.println(SFirstName);
	}
	
	@FXML
	public void LastNameSetter(ActionEvent Event) throws IOException{
		SLastName = LastName.getText(); 
	}
	
	@FXML
	public void EmailSetter(ActionEvent Event) throws IOException{
		SEmail = Email.getText();
	}
	
	@FXML
	public void PhoneNumSetter(ActionEvent Event) throws IOException{
		SNumber = PhoneNumber.getText();
	}
	
	
	
	@FXML
	  public void CreditCardSetter(ActionEvent Event) throws IOException{
		SCard = CreditCardNumber.getText();
		CreditCardError.setText("");
		if(SCard.length() == 16) {
		// Only start checking if we have a full length number
			if(!CreditCardChecker()) {
			CreditCardError.setTextFill(Color.color(1, 0, 0));
		    CreditCardError.setText("INVALID CARD NUMBER");
		}
		else
			CreditCardError.setText(""); //Make it empty if no errors
		}
	}
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
	
	
	

	
	@FXML
	public void CheckAvailability() throws IOException {
		// Go through HotelRoom table, check if type is available. 
		// We need a boolean value for rooms for this.
		Excel obj = new Excel();
	    RadioButton ChosenRoom = (RadioButton) Room.getSelectedToggle();
	    String ChosenOne = ChosenRoom.getText();
		for(int i = 1 ; i < 30 ; i++) {
			String Testin = obj.getCell("Rooms", i, 1).getStringCellValue();
			String Room = obj.getCell("Rooms", i, 4).getStringCellValue();
			if(Room.equals(ChosenOne) && Testin.equals("")) {
				RoomAvailabilityChecker.setText("Open Slot Available!");
				return;
			}
		
		}// End of For Loop
		RoomAvailabilityChecker.setTextFill(Color.color(1, 0, 0));
		RoomAvailabilityChecker.setText("No Open Slots Available!");
		
	}
	
	
	/**
	 *  To Do later. Done to validate if entered data is valid for usage.
	 *  As in. Is a phone number an actual phone number, a credit card valid, etc.
	 */
	//public boolean ValidateInfo() {
		
	//}
	
	
	/*
	 * THINGS WE NEED FOR THIS:
	 * A credit card validation method
	 * Store info
	 * Create some form of error output for wrong user information
	 * We could use the unfocus of element for that
	 * a function to write a new row to the victims table for any new customer
	 * Receipt/ manager class <-- Get a manager class working
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