package application;
 


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;


/**
 * 
 * @author Aidan Fox
 * Date: 4/20/24
 * Description: Controller class for the frontend page displaying available rooms for the User to selcet from.
 */
public class RoomSelectionController {
	private boolean canContinue = false;
	private boolean canConfirm = false;
	private Stage stage;
	private Scene scene;
	private Parent root;
	private HotelRoom chosenRoom = new HotelRoom();
	
	@FXML
	private ToggleGroup Room;
	
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
	private Button CheckAvailability;
	
	@FXML
	private Button Confirm;
	
	@FXML
	private Label DateChecker;
	
	@FXML
	private Label RoomAvailabilityChecker;
	
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	@FXML
	public void CheckInDateEvent(ActionEvent Event) throws IOException {
		checkIn = CheckInDate.getValue();
		if(checkOut != null)
	 		ValidateDates();
		
	}
	
	@FXML
	public void CheckOutDateEvent(ActionEvent Event) throws IOException {
		checkOut = CheckOutDate.getValue();
		if(checkIn != null)
			ValidateDates();
	}
	
	@FXML
	public void SingleButtonPress(ActionEvent Event) throws IOException{
	    chosenRoom.setHotelCost(110);
	    chosenRoom.setHotelType(RoomTypes.SINGLE);
		
		
	}
	@FXML
	public void DoubleButtonPress(ActionEvent Event) throws IOException {
		chosenRoom.setHotelCost(190);
		chosenRoom.setHotelType(RoomTypes.DOUBLE);
	
	}
	@FXML
	public void KingButtonPress(ActionEvent Event) throws IOException {
		chosenRoom.setHotelCost(225);
		chosenRoom.setHotelType(RoomTypes.KING);
		
	}
	@FXML
	public void SuiteButtonPress(ActionEvent Event) throws IOException {
		chosenRoom.setHotelCost(310);
		chosenRoom.setHotelType(RoomTypes.SUITE);
		
	}
	
	@FXML
	/**
	 * Authors: Aidan Fox
	 * Date: 4/20/24
	 * Description: Ensures that the selected dates are in the correct order.
	 * @throws IOException
	 */
	public void ValidateDates() throws IOException {
		//Check if CheckOut is later than the CheckIn
		if(!checkOut.isAfter(checkIn)) {
			DateChecker.setTextFill(Color.color(1,0,0));
			DateChecker.setText("Invalid Dates!");
			canContinue = false;
		}
		else {
			DateChecker.setText("");
			chosenRoom.SetCheckInDate(checkIn);
			chosenRoom.SetCheckOutDate(checkOut);
			canContinue = true;
		}
		
	}
		
	@FXML
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: Method for checking the availability of the currently selected Reservation pair of dates for any room of the selected type. If a room is found, it is set as the chosen room.
	 *              Otherwise, tells the user that the selected type and dates are unavailable.
	 * @throws IOException
	 */
	public void CheckAvailability() throws IOException {
		if(!canContinue) {return;}
		// Go through HotelRoom table, check if type is available. 
		// We need a boolean value for rooms for this.
		Excel e = new Excel();
		char typeID = ' ';
		switch(chosenRoom.getHotelType()) {
		case SINGLE:
			typeID = '1';
			break;
		case DOUBLE:
			typeID = '2';
			break;
		case KING:
			typeID = '3';
			break;
		case SUITE:
			typeID = '4';
			break;
		default:
			RoomAvailabilityChecker.setTextFill(Color.color(1, 0, 0));
			RoomAvailabilityChecker.setText("Please choose a room type!");
			return;
		}
		for(int i = 1 ; i < 30 ; i++) {
			if(e.getCell("Rooms", i, 0) != null) {
				int ID = (int)e.getCell("Rooms", i, 0).getNumericCellValue();
				//System.out.println(e.getCell("Rooms", i, 0).getStringCellValue());
				//System.out.println(typeID + " = " + ID.charAt(0));
				if(typeID == (ID+"").charAt(0)) {
					chosenRoom.setRoomID(ID);
					chosenRoom.SetRow(i);
					boolean available = checkRoomAvailability(chosenRoom, checkIn, checkOut);
					if(available) {
						RoomAvailabilityChecker.setTextFill(Color.color(0, 1, 0));
						RoomAvailabilityChecker.setText("Room Is Available!");
						canConfirm = true;
						return;
					}
				}
			}
		
		}// End of For Loop
		RoomAvailabilityChecker.setTextFill(Color.color(1, 0, 0));
		RoomAvailabilityChecker.setText("No Open Slots Available!");
		canConfirm = false;
		
	}
	
	@FXML
	/**
	 * Author: Sebastian Sunga
	 * Date: 4/15/24
	 * Description: Switches the current page to the Home Page.
	 * @param event
	 * @throws IOException
	 */
    public void switchToHomePage(ActionEvent event) throws IOException {
		     
            root = FXMLLoader.load(getClass().getResource("HomePage.FXML"));
           
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	@FXML
	/**
	 * Author: Sebastian Sunga
	 * Date: 4/15/24
	 * Description: Switches the current page to the UserInfo page.
	 * @param event
	 * @throws IOException
	 */
    public void switchToUserInfoScreen(ActionEvent event) throws IOException {
           // root = FXMLLoader.load(getClass().getResource("UserInfoPage.FXML"));
		if(canConfirm) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInfoPage.FXML"));
            root = loader.load();         
            
            UserInfoController UserInfoController = loader.getController();
            UserInfoController.HotelGrabber(chosenRoom);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
		}    
    }
	
	
	/**
	 * Author: Aidan Fox
	 * Date: 4/20/24
	 * Description: For a given room in the Rooms spreadsheet, we iterate through each pair of dates to check for two conditions:
	 * 					-1: the new check In date is after the old check in date, and it is before the old check out date.
	 * 						-return false.
	 * 					-2: the new check Out date is after the old check in date, and it is before the old check Out date.
	 * 						-return false.
	 * 				After this runs for every pair of dates, and has not returned, we return true, signaling that the reservation is available.
	 * @param room
	 * @param checkIn
	 * @param checkOut
	 * @return boolean
	 */
	protected boolean checkRoomAvailability(HotelRoom room, LocalDate checkIn, LocalDate checkOut) {
		// Check to make sure requested check in and check out dates do not overlap.
		Excel e = new Excel();
		int i = 1;
		int row = room.GetRow();
		while(e.getCell("Rooms", row, i) != null && e.getCell("Rooms", row, i).getStringCellValue() != "") {
			if(room.compareDate(checkIn, e.getCell("Rooms", row, i).getStringCellValue()) >= 0 &&
					room.compareDate(checkIn, e.getCell("Rooms", row, i + 1).getStringCellValue()) <= 0) {
				
				System.out.println(e.getCell("Rooms", row, i).getStringCellValue() + " < " + checkIn + " < " + e.getCell("Rooms", row, i + 1).getStringCellValue());
				
				return false;
			}
			if(room.compareDate(checkOut, e.getCell("Rooms", row, i + 1).getStringCellValue()) <= 0 &&
					room.compareDate(checkOut, e.getCell("Rooms", row, i).getStringCellValue()) >= 0) {
				
				System.out.println(e.getCell("Rooms", row, i).getStringCellValue() + " < " + checkOut + " < " + e.getCell("Rooms", row, i + 1).getStringCellValue());
				
				return false;
			}
			i += 2;
		}
		
		return true;
	}
	
	
	
	
	//Check availability would be getting what radio button was chosen, then searching through that type for any available slots
	// Should we add a boolean condition to hotel rooms, so we can check as in example, double and true?
}