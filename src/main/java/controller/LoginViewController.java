package controller;

import javafx.event.ActionEvent;
import tools.SceneManager;

public class LoginViewController {

    public void openSignUpPage(ActionEvent event) {
        SceneManager.switchToView(event, "signup-view.fxml", 900, 600);
    }

    public void openResetPassPage() {
        SceneManager.openView("reset-pass.fxml", 460, 240);
    }
}