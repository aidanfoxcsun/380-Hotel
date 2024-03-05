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

public class MainPageController  extends Application {
	@Override
    public void start(Stage stage) throws Exception {
        // Group root = new Group();
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("HomePage.FXML"));
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
}