package controller;

import javafx.event.ActionEvent;
import tools.SceneManager;

public class SignUpViewController {
    public void openLoginPage(ActionEvent event) {
        SceneManager.switchToView(event, "login-view.fxml", 900, 600);
    }
}