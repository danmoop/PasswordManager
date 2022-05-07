package application.model;

import java.io.Serializable;
import java.util.Date;

public class Website implements Serializable {
    private String username;
    private String email;
    private String websiteName;
    private Password password;
    private final long creationDate;

    public Website(String username, String email, String websiteName, Password password) {
        this.username = username;
        this.email = email;
        this.websiteName = websiteName;
        this.password = password;
        this.creationDate = new Date().getTime();
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}