package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class RoomSelectionController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void start(Stage stage) throws Exception {
        //Group root = new Group();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RoomSelectionPage.FXML"));
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
	@FXML
    public void switchToUserInfoScreen(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("UserInfoPage.FXML"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
	
}
