module com.owem.experiment04 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.owem.experiment04 to javafx.fxml;
    exports com.owem.experiment04;
}