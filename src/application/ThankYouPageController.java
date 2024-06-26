package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ThankYouPageController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	
	/*
	 * Author: Matthew Barsoum
	 * Date 03/16/2024
	 * Description: Once Home button is clicked, screen switches to Home screen
	 * @param Event
	 * throws IOException
	 * */
	@FXML
    public void switchToHomePage(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("/application/HomePage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
