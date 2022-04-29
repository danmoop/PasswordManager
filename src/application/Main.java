package application;

import application.model.Password;
import application.model.PasswordSettings;
import application.model.User;
import application.tools.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        System.out.println("hey");
        User user = new User("dan@gmail.com", new Password("123", 125801251252L), "123", "123", new PasswordSettings(1, 2, 3, true, true));

        DatabaseManager databaseManager = DatabaseManager.getInstance();

        //databaseManager.add(user);

        for (int i = 0; i < databaseManager.getUsers().size(); i++) {
            System.out.println(databaseManager.getUsers().get(i));
        }

        /*try {
            Pane root = (Pane) FXMLLoader.load(getClass().getResource("views/signUpView.fxml"));
            Scene scene = new Scene(root, 900, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
