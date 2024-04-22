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

public class CancelRoomTwoController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
    public void switchToHomePage(ActionEvent event) throws IOException {
		 // SHOULD CANCEL BOOKING AND SEND TO HOME PAGE

            root = FXMLLoader.load(getClass().getResource("HomePage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	
	
	@FXML
	 public void switchToCancelRoomScreen(ActionEvent event) throws IOException {	
		// GOES BACK TO CANCELL ROOM SCREEN 1, (TO CHANGE ROOM# or CONTINUE BACK TO HOME)
         root = FXMLLoader.load(getClass().getResource("CancellationPage.FXML"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
	 }

	
	@FXML
    public void switchToSelectionScreen(ActionEvent event) throws IOException {			
		 // SHOULD CANCEL BOOKING AND SEND TO ROOM SELECTION SCREEN
            root = FXMLLoader.load(getClass().getResource("RoomSelectionPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	
	
}