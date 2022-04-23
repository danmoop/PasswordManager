module com.cs151.team2.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.cs151.team2.application to javafx.fxml;
    exports com.cs151.team2.application;
    exports controller;
    opens controller to javafx.fxml;
}