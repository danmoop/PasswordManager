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

    public void openSignUpPage(ActionEvent event) {
        SceneManager.switchToView(event, "views/signUpView.fxml", 900, 600);
    }

    public void openResetPassPage(ActionEvent event) {
        SceneManager.openView("views/resetPassView.fxml", 460, 460);
    }

    public void login(ActionEvent event) throws IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();

        User user = databaseManager.findByEmail(emailField.getText());

        if (user == null || !user.getPassword().getPassword().equals(passField.getText())) {
            SceneManager.showAlert("Email / password are incorrect");
        } else {
            DatabaseManager.setActiveUser(user);
            SceneManager.switchToView(event, "views/mainView.fxml", 900, 600);
        }
    }
}