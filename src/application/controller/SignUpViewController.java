package application.controller;

import application.model.Password;
import application.model.PasswordSettings;
import application.model.User;
import application.tools.DBConnection;
import application.tools.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.sqlite.core.DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class SignUpViewController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField questionField;

    @FXML
    private TextField answerField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button signupBtn;

    public void openLoginPage(ActionEvent event) {
        SceneManager.switchToView(event, "views/loginView.fxml", 900, 600);
    }

    public void register() throws SQLException {
        System.out.println("Email: " + emailField.getText());
        DBConnection connection = DBConnection.getInstance();
    }
}