package application;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.util.HashMap;

public class MainPageController extends Application {
	//@FXML
	//private Button roomSelection;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@Override
    public void start(Stage stage) throws Exception {
        //Group root = new Group();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/HomePage.FXML"));
            stage = new Stage();
            //Image topIcon = new Image("HomePage.png");
            Scene mainScreenScene = new Scene(root);
            // stage.getIcons().add(topIcon);
            stage.setTitle("The Phantom Inn");
            stage.setScene(mainScreenScene);
            stage.show();
            stage.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/*
	 * Author: Matthew Barsoum
	 * Date 03/16/2024
	 * Description: Once start your booking button is clicked, screen switches to selection screen
	 * @param Event
	 * throws IOException
	 * */
	@FXML
    public void switchToSelectionScreen(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("/application/RoomSelectionPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	
	   /**
		 * Author: Alexander Chakmakian
		 * Date: 04/18/2024
		 * Description: Sends user to support page upon button click
		 * @param Event
		 * @throws IOException
		 */
	@FXML
	 public void switchToSupportScreen(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("/application/Support.FXML"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
	 }
	@FXML
	 public void switchToCancelRoomScreen(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("/application/CancellationPage.FXML"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
	 }
}