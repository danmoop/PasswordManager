package controller;

import com.cs151.team2.application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {

    public void openSignUpPage(ActionEvent event) {
        switchToView(event, "signup-view.fxml");
    }

    private void switchToView(ActionEvent event, String viewName) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(viewName));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}