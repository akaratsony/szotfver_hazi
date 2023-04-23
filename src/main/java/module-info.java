module com.sakkjatek.jatek {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sakkjatek.jatek to javafx.fxml;
    exports com.sakkjatek.jatek;
}