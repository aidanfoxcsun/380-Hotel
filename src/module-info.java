
module ThePhantomInn {
    requires javafx.controls;
    requires javafx.graphics;
    //requires java.desktop;
    requires javafx.fxml;
    requires javafx.base;
    requires org.apache.poi.poi;
	requires jakarta.mail;

    opens application to javafx.graphics, javafx.fxml;
    exports application;
}