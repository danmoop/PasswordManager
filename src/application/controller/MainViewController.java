package application.controller;

import application.tools.DatabaseManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Text middleScreenText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseManager databaseManager = null;
        try {
            databaseManager = DatabaseManager.getInstance();
            middleScreenText.setText("Hey, " + databaseManager.getActiveUser().getEmail());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}