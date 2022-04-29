package application.controller;

import application.model.PasswordSettings;
import application.model.User;
import application.tools.DatabaseManager;
import application.tools.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SetPassSettingsController {

    @FXML
    private TextField minPassLengthField;

    @FXML
    private TextField maxPassLengthField;

    @FXML
    private CheckBox useUpperLetters;

    @FXML
    private CheckBox useSpecChars;

    @FXML
    private TextField upperCharsNumField;

    /**
     * This function finishes the registration by asking the settings for generating a password
     * After fetching the values, check whether they are correct and set the settings for the user
     * Save the user and redirect to a login view to let them sign in
     *
     * @param event is used to identify which view should be replaced with a new one
     */
    public void confirmSettings(ActionEvent event) throws IOException {
        int minPassLength = Integer.parseInt(minPassLengthField.getText());
        int maxPassLength = Integer.parseInt(maxPassLengthField.getText());
        int numOfUpperChars = Integer.parseInt(upperCharsNumField.getText());

        PasswordSettings passwordSettings = new PasswordSettings(minPassLength, maxPassLength, numOfUpperChars, useSpecChars.isSelected(), useUpperLetters.isSelected());

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        User user = databaseManager.getUsers().get(databaseManager.getUsers().size() - 1);
        user.setPasswordSettings(passwordSettings);
        databaseManager.set(user.getEmail(), user);

        if (areSettingsCorrect(minPassLength, maxPassLength, numOfUpperChars)) {
            SceneManager.switchToView(event, "views/loginView.fxml", 900, 600);
            SceneManager.showAlert("Registered successfully");
        } else {
            SceneManager.showAlert("Incorrect settings!");
        }
    }

    /**
     * @return whether the values are correct and are in a valid range
     */
    private boolean areSettingsCorrect(int minPassLength, int maxPassLength, int numOfUpperChars) {
        if (minPassLength > maxPassLength) return false;
        return numOfUpperChars <= maxPassLength && numOfUpperChars >= minPassLength;
    }
}