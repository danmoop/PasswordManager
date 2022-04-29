package application.controller;

import application.model.User;
import application.tools.DatabaseManager;
import application.tools.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Text middleScreenText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            middleScreenText.setText("Hey, " + databaseManager.getActiveUser().getEmail());
            checkForExpiredPasswords(databaseManager.getActiveUser());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent event) throws IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.setActiveUser(null);
        SceneManager.switchToView(event, "views/loginView.fxml", 900, 600);
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
            SceneManager.showAlert("Expired Passwords", sb.toString());
        }
    }
}