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
        String lastNameInput = LastName.getText();
        String roomNumberInput = RoomNumber.getText();

        // Check if last name and room number match the expected values
        if ("expectedLastName".equals(lastNameInput) && "expectedRoomNumber".equals(roomNumberInput)) {
            root = FXMLLoader.load(getClass().getResource("CancellationPageTwo.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // Show an alert for invalid input
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Last name or room number is incorrect. Please try again.");
            alert.showAndWait();
        }
    }
}
