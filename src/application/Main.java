package application;

import application.model.User;
import application.tools.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.init();

        List<User> users = databaseManager.getUsers();

        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }

        try {
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("views/signUpView.fxml"));
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
