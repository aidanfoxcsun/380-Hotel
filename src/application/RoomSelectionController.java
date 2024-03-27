package application;
 
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class RoomSelectionController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
    public void switchToUserInfoScreen(ActionEvent event) throws IOException {
		
		RadioButton ChosenRoom = (RadioButton) RoomType.getSelectedToggle();
	    String ChosenOne = ChosenRoom.getText();
		
		
            root = FXMLLoader.load(getClass().getResource("UserInfoPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

	
	
	
	
    private HotelRoom hotelRoom = new HotelRoom();
	
	
	@FXML
	private RadioButton SingleButton;
	@FXML
	private RadioButton DoubleButton;
	@FXML
	private RadioButton KingButton;
	@FXML
	private RadioButton SuiteButton;
	@FXML
	private RadioButton UserChoice;
	@FXML
	private DatePicker CheckInDate;
	@FXML
	private DatePicker CheckOutDate;
	@FXML
	private ToggleGroup RoomType;
	
	@FXML
	public void SingleButtonPress(ActionEvent Event) throws IOException{
	
		hotelRoom.setHotelCost(110);
		// System.out.println(hotelRoom.getHotelType());
		//System.out.println(hotelRoom.getHotelCost());
	}
	
	@FXML
	public void DoubleButtonPress(ActionEvent Event) throws IOException {
		
		hotelRoom.setHotelCost(190);
		//System.out.println(Choice);
	}
	
	@FXML
	public void KingButtonPress(ActionEvent Event) throws IOException {

		hotelRoom.setHotelCost(225);
		//System.out.println("King Room chosen!");
	}
	
	@FXML
	public void SuiteButtonPress(ActionEvent Event) throws IOException {
	
		hotelRoom.setHotelCost(310);
		//System.out.println("Suite button chosen");
	}
	
	@FXML
	public void CheckInDateEvent(ActionEvent Event) throws IOException {
		LocalDate date = CheckInDate.getValue();
		hotelRoom.SetCheckInDate(date);
	}
	
	@FXML
	public void CheckOutDateEvent(ActionEvent Event) throws IOException {
		LocalDate date = CheckOutDate.getValue();
		hotelRoom.SetCheckOutDate(date);
	}
	
	
	@FXML
	public void CheckAvailability(MouseEvent Event) throws IOException {
		// Go through HotelRoom table, check if type is available. 
		// We need a boolean value for rooms for this.
		Excel obj = new Excel();
	    RadioButton ChosenRoom = (RadioButton) RoomType.getSelectedToggle();
	    String ChosenOne = ChosenRoom.getText();
		for(int i = 1 ; i < 30 ; i++) {
			String Testin = obj.getCell("Rooms", i, 4).getStringCellValue();
			String Room = obj.getCell("Rooms", i, 1).getStringCellValue();
			if(Room.equals(ChosenOne) && Testin.equals("Y")) {
				System.out.println("Room Available!");
				return;
			}
		
		}// End of For Loop
		System.out.println("All slots filled!");
		
	}
	
	
	
	//Check availability would be getting what radio button was chosen, then searching through that type for any available slots
	// Should we add a boolean condition to hotel rooms, so we can check as in example, double and true?
}
