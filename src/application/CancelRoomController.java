package application;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CancelRoomController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// Take in user's room number
	
	@FXML
	private TextField LastName;
	
	@FXML
	private TextField RoomNumber;
	
	
	@FXML
    public void switchToHomePage(ActionEvent event) throws IOException {
			
			root = FXMLLoader.load(getClass().getResource("HomePage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	
	@FXML
	public void switchToCancelRoomTwoScreen(ActionEvent event) throws IOException {	
	// IF - ELSE STATEMENT, IF VALID ROOM NUMBER, SENDS YOU TO "CancellationPageTwo.FXML"
	
			root = FXMLLoader.load(getClass().getResource("CancellationPageTwo.FXML"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	}
}