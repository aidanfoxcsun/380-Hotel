package application;
 


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.time.LocalDate;
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
    public void switchToHomePage(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("HomePage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	@FXML
    public void switchToUserInfoScreen(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("UserInfoPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	
	
	// This method should be called for each room of a specified type to see if a reservation is available.
	protected boolean checkAvailability(HotelRoom room, LocalDate checkIn, LocalDate checkOut) {
		// Check to make sure requested check in and check out dates do not overlap.
		Excel e = new Excel();
		int i = 1;
		while(e.getCell("Rooms", room.GetRow(), i) != null) {
			if(room.compareDate(checkIn, e.getCell("Rooms", room.GetRow(), i).getStringCellValue()) >= 0 &&
					room.compareDate(checkIn, e.getCell("Rooms", room.GetRow(), i + 1).getStringCellValue()) <= 0) {
				return false;
			}
			if(room.compareDate(checkOut, e.getCell("Rooms", room.GetRow(), i + 1).getStringCellValue()) <= 0 &&
					room.compareDate(checkOut, e.getCell("Rooms", room.GetRow(), i).getStringCellValue()) >= 0) {
				return false;
			}
			i += 2;
		}
		
		return true;
	}
	
	
	
	
	//Check availability would be getting what radio button was chosen, then searching through that type for any available slots
	// Should we add a boolean condition to hotel rooms, so we can check as in example, double and true?
}