module com.owem.experiment05 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.owem.experiment05 to javafx.fxml;
    exports com.owem.experiment05;
    exports com.owem.experiment05.dao;
    opens com.owem.experiment05.dao to javafx.fxml;
    exports com.owem.experiment05.beans;
    opens com.owem.experiment05.beans to javafx.fxml;
}