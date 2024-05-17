package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


public class CancelRoomController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField LastName;

    @FXML
    private TextField RoomNumber;

    
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

    /**
 	 * Author: Alexander Chakmakian
 	 * Date: 04/18/2024
 	 * Description: Sends user to the next cancel room screen if room # and name match a booking.
 	 * Takes in last name and booking number, and the output will be the action of going to the next page.
 	 * @param Event
 	 * @throws IOException
 	 */
    @FXML
    public void switchToCancelRoomTwoScreen(ActionEvent event) throws IOException {
        String lastNameInput = LastName.getText();
        int roomNumberInput = Integer.parseInt(RoomNumber.getText()); // Convert to int

        // Call the findCustomer method from Customer class
        Customer customer = Customer.findCustomer(lastNameInput, roomNumberInput);

        if (customer != null) {
            // If customer is found, navigate to the next cancellation page
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CancellationPageTwo.FXML"));
            root = loader.load();
            
            CancelRoomTwoController cancelRoomTwoController = loader.getController();
            cancelRoomTwoController.InfoGrabber(customer, HotelRoom.FindRoom(RoomNumber.getText()));
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } 
        else {
            // Show an alert for invalid input
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Last name or room number is incorrect. Please try again.");
            alert.showAndWait();
        }
    }

}
