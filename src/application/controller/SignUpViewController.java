package application.controller;

import application.model.Password;
import application.model.User;
import application.tools.DatabaseManager;
import application.tools.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class SignUpViewController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField questionField;

    @FXML
    private TextField answerField;

    @FXML
    private PasswordField passField;

    private final long ONE_MONTH = 31L * 86400 * 1000;

    public void openLoginPage(ActionEvent event) {
        SceneManager.switchToView(event, "views/loginView.fxml", 900, 600);
    }

    public void register(ActionEvent event) throws SQLException, IOException {
        String email = emailField.getText();
        String question = questionField.getText();
        String answer = answerField.getText();
        String pass = passField.getText();

        DatabaseManager databaseManager = DatabaseManager.getInstance();

        if (databaseManager.findByEmail(email) == null) {
            long date = new Date().getTime();
            User user = new User(email, new Password(pass, date + ONE_MONTH * 3), question, answer, null);
            databaseManager.add(user);

            SceneManager.switchToView(event, "views/setPassSettings.fxml", 900, 600);
        } else {
            SceneManager.showAlert("This email is already registered");
        }
    }
}