module com.owem.experiment {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.dbutils;
    requires java.sql;
    requires mysql.connector.java;


    opens com.owem.experiment03 to javafx.fxml;
    exports com.owem.login;
}