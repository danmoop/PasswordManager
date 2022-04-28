package application.controller;

import application.tools.SceneManager;
import javafx.event.ActionEvent;

public class SignUpViewController {
    public void openLoginPage(ActionEvent event) {
        SceneManager.switchToView(event, "login-view.fxml", 900, 600);
    }
}
