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
	
	   /**
		 * Author: Alexander Chakmakian
		 * Date: 04/18/2024
		 * Description: Cancels room and sends user to home page upon button click
		 * @param Event
		 * @throws IOException
		 */
	@FXML
    public void switchToHomePage(ActionEvent event) throws IOException {
		 // ONLY GOES TO HOME
            root = FXMLLoader.load(getClass().getResource("HomePage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	
	   /**
		 * Author: Alexander Chakmakian
		 * Date: 04/19/2024
		 * Description: Sends user back to cancel room screen 1
		 * @param Event
		 * @throws IOException
		 */
	@FXML
	 public void switchToCancelRoomScreen(ActionEvent event) throws IOException {	
		// ONLY GOES BACK TO CANCEL ROOM SCREEN 1
         root = FXMLLoader.load(getClass().getResource("CancellationPage.FXML"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
	 }

	   /**
		 * Author: Alexander Chakmakian
		 * Date: 04/19/2024
		 * Description: Cancels booking and sends user to selection room screen upon button click
		 * @param Event
		 * @throws IOException
		 */
	@FXML
    public void switchToSelectionScreen(ActionEvent event) throws IOException {			
		 // SHOULD CANCEL BOOKING AND SEND TO ROOM SELECTION SCREEN
            root = FXMLLoader.load(getClass().getResource("RoomSelectionPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	
	   /**
	      * Author: Alexander Chakmakian
		  * Date: 04/22/2024
	      * Description: Cancels room and sends user to home page upon button click
	      * @param Event
          * @throws IOException
	   */
		@FXML
	    public void CancelRoomswitchToHomePage(ActionEvent event) throws IOException {
			 // ONLY GOES TO HOME
	            root = FXMLLoader.load(getClass().getResource("HomePage.FXML"));
	            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	            scene = new Scene(root);
	            stage.setScene(scene);
	            stage.show();
	    }
	
}


