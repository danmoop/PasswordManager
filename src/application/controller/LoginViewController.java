package application.controller;

import application.tools.SceneManager;
import javafx.event.ActionEvent;

public class LoginViewController {
    public void openSignUpPage(ActionEvent event) {
        SceneManager.switchToView(event, "views/signUpView.fxml", 900, 600);
    }

    public void openResetPassPage(ActionEvent event) {
        SceneManager.openView("views/resetPassView.fxml", 460, 460);
    }
}