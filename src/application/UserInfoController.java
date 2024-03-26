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

public class UserInfoController{
	private Stage stage;
	private Scene scene;
	private Parent root;
	private Label firstName, lastName, email, phoneNumber,  room, card;
	private String SFirstName, SLastName, SEmail, SNumber, SRoom, SCard;
	
	@FXML
	public void confirmRoom(ActionEvent event) throws IOException {
		Customer cust = new Customer(SFirstName, SLastName, SEmail, SNumber, SRoom, SCard);
	}
	
	public void updateUserInfo() {
		SFirstName = firstName.getText();
		SLastName = lastName.getText();
		SEmail = email.getText();
		SNumber = phoneNumber.getText();
		SRoom = room.getText();
		SCard = card.getText();
	}
	
	
	
	
	
	
	
	
	@FXML
    public void switchToRoomSelectionScreen(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("RoomSelectionPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}