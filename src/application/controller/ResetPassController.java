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
}