package application.tools;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    /**
     * This function switches to a new specified view
     *
     * @param event    is used to identify which view should be replaced with a new one
     * @param viewName is a name of the new view that will be loaded
     * @param width    is width of a new view window
     * @param height   is height of a new view window
     */
    public static void switchToView(ActionEvent event, String viewName, int width, int height) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(viewName));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The function shows an alert
     *
     * @param message is contents of the alert message
     */
    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setContentText(message);
        alert.show();
    }

    /**
     * The function shows an alert
     *
     * @param title   is a title of an alert
     * @param message is contents of the alert message
     */
    public static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}