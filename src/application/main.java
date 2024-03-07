package application;
import java.io.IOException;
import javafx.application.Application;


public class main{

	public static void main(String[] args) {
		Application.launch(MainPageController.class, args);
		Excel obj = new Excel();	
	}
}