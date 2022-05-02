package application;

import application.tools.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        // Initialize the database to create a flat file if it doesn't exist
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.init();

        try {
            // Load the signup view
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("views/signUpView.fxml"));
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
