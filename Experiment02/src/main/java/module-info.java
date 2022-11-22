module com.owem.experiment02 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.owem.experiment02 to javafx.fxml;
    exports com.owem.experiment02;
    exports com.owem.experiment02.login;
    exports com.owem.experiment02.clock;
    opens com.owem.experiment02.login to javafx.fxml;
}