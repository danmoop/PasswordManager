package application.controller;

import application.model.Password;
import application.model.User;
import application.tools.DatabaseManager;
import application.tools.SceneManager;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
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

    // User is redirected to a login view
    public void openLoginPage(ActionEvent event) {
        SceneManager.switchToView(event, "views/loginView.fxml", 900, 600);
    }

    /**
     * This function allows user to register an account
     * The function checks that there are no accounts created with the identical email
     * Show an alert with such an account already exists
     * After the checks have been completed, redirect the user to set up pass generation settings
     *
     * @param event is used to identify which view should be replaced with a new one
     */
    public void register(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String question = questionField.getText();
        String answer = answerField.getText();
        String pass = passField.getText();

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        PassUtil passUtil = new PassUtil();

        if (databaseManager.findByEmail(email) == null) {
            long date = new Date().getTime();
            User user = new User(email, new Password(passUtil.encrypt(pass), date + ONE_MONTH * 3), question, answer, null);
            databaseManager.add(user);

            SceneManager.switchToView(event, "views/setPassSettings.fxml", 900, 600);
        } else {
            SceneManager.showAlert("This email is already registered");
        }
    }
}