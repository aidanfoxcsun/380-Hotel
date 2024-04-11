package application;
 


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;



public class RoomSelectionController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	protected HotelRoom HotelRoom = new HotelRoom();
	
	 @FXML
	 private DatePicker CheckInDate;
	 @FXML
	 private DatePicker CheckOutDate;
	 
	 @FXML
	 private ToggleGroup HotelType;
	 
	 @FXML
	  private RadioButton SingleType;
	 @FXML
	 private RadioButton DoubleType;
	 @FXML
	 private RadioButton KingType; 
	 @FXML
	 private RadioButton SuiteType;
	 
	 @FXML
	 private Label DateError;
	 
	  @FXML
	  public void Initilize() {
		  SingleType.setUserData("1");
		  DoubleType.setUserData("2");
		  KingType.setUserData("3");
		  SuiteType.setUserData("4");
		  DateError.setText("");
	  }
	
	@FXML
	public void CheckInDateEvent(ActionEvent Event) throws IOException {
		LocalDate date = CheckInDate.getValue();
		HotelRoom.SetCheckInDate(date);
	}
	
	@FXML
	public void CheckOutDateEvent(ActionEvent Event) throws IOException {
		LocalDate date = CheckInDate.getValue();
		HotelRoom.SetCheckOutDate(date);
	}
	
	@FXML
	public void CheckerOfDates() throws IOException {
		LocalDate CheckIn = CheckInDate.getValue();
		LocalDate CheckOut = CheckOutDate.getValue();
		if(CheckIn.isAfter(CheckOut)) {
			DateError.setTextFill(Color.color(1, 0, 0));
			DateError.setText("Invalid Dates!");
		}
		else
			DateError.setText("");
		
		
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
    public void switchToUserInfoScreen(ActionEvent event) throws IOException {
           // root = FXMLLoader.load(getClass().getResource("UserInfoPage.FXML"));
             FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInfoPage.fxml"));
            root = loader.load();
            
            UserInfoController UserInfoController = loader.getController();
            UserInfoController.HotelGrabber(HotelRoom);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	

	
	
	
	
	
	//Check availability would be getting what radio button was chosen, then searching through that type for any available slots
	// Should we add a boolean condition to hotel rooms, so we can check as in example, double and true?
}