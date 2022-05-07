package application.controller;

import application.model.User;
import application.model.Website;
import application.tools.DatabaseManager;
import application.tools.SceneManager;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainViewController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchField;

    private DatabaseManager databaseManager;

    // An Initializable is implemented to perform an operation after the view has been loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            databaseManager = DatabaseManager.getInstance();
            User user = databaseManager.getActiveUser();
            checkForExpiredPasswords(user);
            displayWebsites(user.getWebsites());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function displays all the websites added by a user on the main view
     * After the active authentication has been fetched, get all the user's websites
     * Check for expired passwords and notify if there are any by showing an alert
     * Display each stored account in a vbox on a scroll pane
     */
    private void displayWebsites(List<Website> websites) throws IOException {
        User user = databaseManager.getActiveUser();

        PassUtil passUtil = new PassUtil();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        VBox vBox = new VBox();
        vBox.setSpacing(15);


        for (int i = 0; i < websites.size(); i++) {
            HBox hBox = new HBox();
            Text websiteName = new Text("Website: " + websites.get(i).getWebsiteName());
            Text username = new Text("Username: " + websites.get(i).getUsername());
            Text email = new Text("Email: " + websites.get(i).getEmail());

            String userPass = websites.get(i).getPassword().getPassword();

            Text pass = new Text("Password: " + userPass.substring(0, Math.min(15, userPass.length())) + "...");
            Button copyBtn = new Button("Copy");
            Button deleteBtn = new Button("Delete");
            deleteBtn.setStyle("-fx-background-color: #5757e4; -fx-text-fill: white; -fx-font-weight: bold");
            copyBtn.setMinWidth(100);
            deleteBtn.setMinWidth(100);

            int finalI = i;
            deleteBtn.setOnAction(event -> {
                user.getWebsites().remove(websites.get(finalI));
                databaseManager.set(user.getEmail(), user);
                SceneManager.switchToView(event, "views/mainView.fxml", 1200, 700);
            });

            copyBtn.setOnAction(event -> {
                String decrypted = passUtil.decrypt(userPass);
                StringSelection stringSelection = new StringSelection(decrypted);
                clipboard.setContents(stringSelection, null);
            });

            hBox.getChildren().add(websiteName);
            hBox.getChildren().add(username);
            hBox.getChildren().add(email);
            hBox.getChildren().add(pass);
            hBox.getChildren().add(copyBtn);
            hBox.getChildren().add(deleteBtn);
            hBox.setAlignment(Pos.CENTER);

            hBox.setSpacing(20);

            vBox.getChildren().add(hBox);
            scrollPane.setContent(vBox);
        }
    }

    /**
     * This functions removes the results of the search and shows all user's projects
     *
     * @throws IOException
     */
    public void reset() throws IOException {
        displayWebsites(databaseManager.getActiveUser().getWebsites());
        searchField.setText("");
    }

    /**
     * This function finds the websitse that match the given criteria
     */
    public void search() throws IOException {
        scrollPane.setContent(null);
        displayWebsites(find(searchField.getText()));
        searchField.setText("");
    }

    /**
     * THis function finds the websites that match the information given by the user
     *
     * @param value is the criteria that is going to be used to find websites
     * @return the list of websites that match the criteria
     */
    private List<Website> find(String value) {
        User user = databaseManager.getActiveUser();

        return user.getWebsites().stream()
                .filter(website -> website.getEmail().contains(value) || website.getWebsiteName().contains(value) || website.getUsername().contains(value))
                .collect(Collectors.toList());
    }

    /**
     * This function sets the active authentic  ation to null and redirects the user to login view
     *
     * @param event is used to identify which view should be replaced with a new one
     */
    public void logout(ActionEvent event) throws IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.setActiveUser(null);
        SceneManager.switchToView(event, "views/loginView.fxml", 900, 600);
    }

    /**
     * This function redirects the user to a page that allows to add an account for an app/website
     *
     * @param event is used to identify which view should be replaced with a new one
     */
    public void addWebsite(ActionEvent event) {
        SceneManager.switchToView(event, "views/addWebsitesView.fxml", 600, 400);
    }

    /**
     * This function checks whether there are any expired passwords
     *
     * @param user is a user, whose passwords are going to be checked
     */
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