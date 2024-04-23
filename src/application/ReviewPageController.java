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
	/**
	 * Author:Sebastian Sunga
	 * Date: 04/14/2024
	 * Description: shows the hotel type from the enum due to needing to convert to string.
	 * @param HotelInfo
	 */
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
	
	 /**
	  * Author: Sebastian Sunga
	  * Date: 04/15/2024
	  * Description: Switch off point for data from UserPageInfoController and this ReviewPageController
	  * @param CustomerInfo
	  * @param HotelInfo
	  */
	 public void InfoGrabber(Customer CustomerInfo, HotelRoom HotelInfo){
		            NewCustomer = CustomerInfo;
		            CFirstName.setText(NewCustomer.getCustomerFirstName());
		            CLastName.setText(NewCustomer.getCustomerLastName());
		            CPhone.setText(NewCustomer.getCustomerPhoneNumber());
		            CEmail.setText(NewCustomer.getCustomerEmail());
		            RoomID.setText(Integer.toString(NewCustomer.getCustomerHotelRoom().getRoomID()));
		            
		            HotelData = HotelInfo;
		            TypeShower(HotelData);
		            
		            //RoomID.setText();
		           
		            HotelCheckIn.setText(HotelData.GetCheckInDate().toString());
		            HotelCheckOut.setText(HotelData.GetCheckOutDate().toString());
		            
		            TheMoney.setText("$" + Integer.toString(HotelData.getHotelCost()));
		            
		            	
		            
		            
		            
		  
	 }
	 
	 
	 
	  
	  /**
	   * Author: Sebastian Sunga, model methods made by Aidan Fox.
	   * Date: 04/15/2024
	   * Description: Writes to the excel sheet with customer data.
	   */
	  public void ConfirmReservation() {
		   NewCustomer.UpdateExcel();
		   HotelData.Reserve(HotelData.GetCheckInDate(), HotelData.GetCheckOutDate());
	  }
	  
	  /*
		 * Author: Matthew Barsoum
		 * Date 03/16/2024
		 * Description: If home button is clicked, screen switches to Home page
		 * @param Event
		 * throws IOException
		 * */
	@FXML
    public void switchToHomePage(ActionEvent event) throws IOException {
		    
		    
            root = FXMLLoader.load(getClass().getResource("HomePage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	/*
	 * Author: Matthew Barsoum
	 * Date 03/16/2024
	 * Description: If back button is clicked, screen switches back to user information screen
	 * @param Event
	 * throws IOException
	 * */
	@FXML
    public void switchToUserInfoPage(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("UserInfoPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	/*
	 * Author: Matthew Barsoum
	 * Date 03/16/2024
	 * Description: Once Book button is clicked, screen switches to the final screen
	 * @param Event
	 * throws IOException
	 * */
	@FXML
    public void switchToThankYouPage(ActionEvent event) throws IOException {
		    ConfirmReservation();
		    
            root = FXMLLoader.load(getClass().getResource("ThankYouPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
