package application.controller;

import application.model.User;
import application.tools.DatabaseManager;
import application.tools.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginViewController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passField;

    // Redirect to sign up page if user has no account yet
    public void openSignUpPage(ActionEvent event) {
        SceneManager.switchToView(event, "views/signUpView.fxml", 900, 600);
    }

    // Redirect to reset pass page if user wants to reset a password
    public void openResetPassPage(ActionEvent event) {
        SceneManager.switchToView(event, "views/resetPassView.fxml", 460, 460);
    }

    /**
     * This function checks if the email and password are valid for the user to sign in
     * If the information is correct, redirect to main view
     * If not correct, show an alert that some info is invalid
     *
     * @param event is used to identify which view should be replaced with a new one
     */
    public void login(ActionEvent event) throws IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();

        User user = databaseManager.findByEmail(emailField.getText());

        if (user == null || !user.getPassword().getPassword().equals(passField.getText())) {
            SceneManager.showAlert("Email / password are incorrect");
        } else {
            databaseManager.setActiveUser(user);
            SceneManager.switchToView(event, "views/mainView.fxml", 900, 600);
        }
    }
}