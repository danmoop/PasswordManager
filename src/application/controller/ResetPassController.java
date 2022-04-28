package application.controller;

import application.tools.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class ResetPassController {
    @FXML
    private TextField emailField;

    @FXML
    private Button confirmEmailBtn;

    @FXML
    private Text securityQuestion;

    @FXML
    private PasswordField newPassField;

    @FXML
    private Button confirmBtn;

    public void connect() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
    }
}