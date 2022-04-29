package application.controller;

import application.model.Password;
import application.model.User;
import application.tools.DatabaseManager;
import application.tools.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Date;

public class ResetPassController {
    @FXML
    private TextField emailField;

    @FXML
    private TextField securityAnswerField;

    @FXML
    private PasswordField newPassField;

    @FXML
    private Text securityQuestion;

    private final long ONE_MONTH = 31L * 86400 * 1000;

    /**
     * This function confirms the existence of an email specified by the user
     * If the email and the account exist, user will be able to provide information to change the password
     * If the email and the account don't exist, show an alert
     */
    public void checkEmail() throws IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();

        User user = databaseManager.findByEmail(emailField.getText());

        if (user != null) {
            securityAnswerField.setDisable(false);
            newPassField.setDisable(false);

            securityQuestion.setText(user.getSecurityQuestion());
        } else {
            SceneManager.showAlert("Incorrect email!");
        }
    }

    /**
     * This function sets a new password after all the checks have been completed before
     * After information has been saved, the user is redirected to a login view
     *
     * @param event is used to identify which view should be replaced with a new one
     */
    public void setPass(ActionEvent event) throws IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        User user = databaseManager.findByEmail(emailField.getText());

        if (!user.getSecurityAnswer().equals(securityAnswerField.getText())) {
            SceneManager.showAlert("Incorrect answer");
        } else {
            long date = new Date().getTime();
            user.setPassword(new Password(newPassField.getText(), date + ONE_MONTH * 3));
            databaseManager.set(emailField.getText(), user);

            SceneManager.showAlert("Success!");
            SceneManager.switchToView(event, "views/loginView.fxml", 900, 600);
        }
    }

    // If user doesn't want to reset a pass, redirect back to a login view
    public void goBack(ActionEvent event) {
        SceneManager.switchToView(event, "views/loginView.fxml", 900, 600);
    }
}