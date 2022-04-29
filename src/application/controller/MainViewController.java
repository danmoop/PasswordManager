package application.controller;

import application.model.User;
import application.model.Website;
import application.tools.DatabaseManager;
import application.tools.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            displayWebsites();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayWebsites() throws IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        User user = databaseManager.getActiveUser();
        checkForExpiredPasswords(user);
        System.out.println(user.getWebsites());
        List<Website> websites = user.getWebsites();

        VBox vBox = new VBox();
        vBox.setSpacing(15);

        for (int i = 0; i < websites.size(); i++) {
            HBox hBox = new HBox();
            Text websiteName = new Text("Website: " + websites.get(i).getWebsiteName());
            Text username = new Text("Username: " + websites.get(i).getUsername());
            Text pass = new Text("Password: " + websites.get(i).getPassword().getPassword());
            Button deleteBtn = new Button("Delete");
            deleteBtn.setStyle("-fx-background-color: #5757e4; -fx-text-fill: white; -fx-font-weight: bold");

            int finalI = i;
            deleteBtn.setOnAction(event -> {
                user.getWebsites().remove(websites.get(finalI));
                databaseManager.set(user.getEmail(), user);
                SceneManager.switchToView(event, "views/mainView.fxml", 900, 600);
            });

            hBox.getChildren().add(websiteName);
            hBox.getChildren().add(username);
            hBox.getChildren().add(pass);
            hBox.getChildren().add(deleteBtn);
            hBox.setAlignment(Pos.CENTER);

            hBox.setSpacing(20);

            vBox.getChildren().add(hBox);
            scrollPane.setContent(vBox);
        }
    }

    public void logout(ActionEvent event) throws IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.setActiveUser(null);
        SceneManager.switchToView(event, "views/loginView.fxml", 900, 600);
    }

    public void addWebsite(ActionEvent event) {
        SceneManager.switchToView(event, "views/addWebsitesView.fxml", 600, 400);
    }

    private void checkForExpiredPasswords(User user) {
        StringBuilder sb = new StringBuilder();

        long date = new Date().getTime();

        for (int i = 0; i < user.getWebsites().size(); i++) {
            if (user.getWebsites().get(i).getPassword().getExpirationDate() < date) {
                sb.append(user.getWebsites().get(i).getWebsiteName()).append("\n");
            }
        }

        if (sb.length() > 0) {
            sb.append("All the passwords for websites above have expired");
            SceneManager.showAlert("Expired Passwords", sb.toString());
        }
    }
}