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
	public void confirmRoom(ActionEvent event) throws IOException {
		Customer cust = new Customer(SFirstName, SLastName, SEmail, SNumber, SRoom, SCard);
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
	public void CreditCardChecker(ActionEvent Event) throws IOException{
		// Figure out credit card validator, because java doesn't like unsigned longs.
		SCard = CreditCardNumber.getText();
	}
	
	/*
	/ I'm thinking that only the fields that require validation get their own methods.
	 *  Basic fields that doesn't require validation can just be grabbed with the next button.
	 */
	
	
	
	
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