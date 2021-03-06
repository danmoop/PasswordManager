package application.controller;

import application.model.Password;
import application.model.User;
import application.model.Website;
import application.tools.DatabaseManager;
import application.tools.PasswordGenerationManager;
import application.tools.SceneManager;
import edu.sjsu.yazdankhah.crypto.util.PassUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Date;

public class AddWebsiteController {

    @FXML
    private TextField websiteNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    private long ONE_MONTH = 31L * 84600;

    /**
     * This function saves a newly created website to a database
     * Then, the user is moved to a main view
     *
     * @param event is used to identify which view should be replaced with a new one
     */
    public void save(ActionEvent event) throws IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        PassUtil passUtil = new PassUtil();

        User user = databaseManager.getActiveUser();
        long date = new Date().getTime();

        String pass = PasswordGenerationManager.generatePass(user.getPasswordSettings());

        Password password = new Password(passUtil.encrypt(pass), date + ONE_MONTH * 3);
        Website website = new Website(usernameField.getText(), emailField.getText(), websiteNameField.getText(), password);

        user.getWebsites().add(website);
        databaseManager.set(user.getEmail(), user);

        SceneManager.switchToView(event, "views/mainView.fxml", 1200, 700);
    }
}