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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReviewPageController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private Label CFirstName; 
	@FXML
	private Label CLastName;
	@FXML
	private Label CPhone;
	@FXML 
	private Label CEmail;
	@FXML
	private Label RoomID;
	@FXML
	private Label HotelType;
	
	@FXML 
    private Label HotelCost;
	
	@FXML
	private Label HotelCheckIn;
	
	@FXML
	private Label HotelCheckOut;
	
	@FXML
	private Label TheMoney;
	
	@FXML
	private Button BookButton; 
	
	Customer NewCustomer = new Customer();
	HotelRoom HotelData = new HotelRoom();
	
	public void TypeShower(HotelRoom HotelInfo) {
		switch(HotelData.getHotelType()) {
        case SINGLE:
        	HotelType.setText("SINGLE");
        	break;
        case DOUBLE:
        	HotelType.setText("DOUBLE");
        	break;
        case KING:
        	HotelType.setText("KING");
        	break;
        case SUITE:
        	HotelType.setText("SUITE");
        	break;
         default:
        	HotelType.setText("Unknown?");
        	return;
        }
	}
	
	 
	 public void InfoGrabber(Customer CustomerInfo, HotelRoom HotelInfo){
		            NewCustomer = CustomerInfo;
		            CFirstName.setText(NewCustomer.getCustomerFirstName());
		            CLastName.setText(NewCustomer.getCustomerLastName());
		            CPhone.setText(NewCustomer.getCustomerPhoneNumber());
		            CEmail.setText(NewCustomer.getCustomerEmail());
		            
		            HotelData = HotelInfo;
		            TypeShower(HotelData);
		            
		            //RoomID.setText();
		           
		            HotelCheckIn.setText(HotelData.GetCheckInDate().toString());
		            HotelCheckOut.setText(HotelData.GetCheckOutDate().toString());
		            
		            TheMoney.setText("$" + Integer.toString(HotelData.getHotelCost()));
		            
		            	
		            
		            
		            
		  
	 }
	 
	 
	  public void Initialize() {
		  CFirstName.setText(NewCustomer.getCustomerFirstName());
		  CLastName.setText(NewCustomer.getCustomerLastName());
		 
	  }
	  
	  @FXML
	  public void ConfirmReservation(ActionEvent Event) {
		   NewCustomer.UpdateExcel();
	  }
	  
	
	@FXML
    public void switchToHomePage(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("HomePage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	@FXML
    public void switchToUserInfoPage(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("UserInfoPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	@FXML
    public void switchToThankYouPage(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("ThankYouPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
