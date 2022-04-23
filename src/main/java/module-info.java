module com.cs151.team2.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cs151.team2.passwordmanager to javafx.fxml;
    exports com.cs151.team2.passwordmanager;
}