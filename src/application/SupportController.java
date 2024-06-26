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

public class SupportController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	   /**
		 * Author: Alexander Chakmakian
		 * Date: 04/18/2024
		 * Description: Sends user to home page upon button click
		 * @param Event
		 * @throws IOException
		 */
	@FXML
    public void switchToHomePage(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("/application/HomePage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	
}
