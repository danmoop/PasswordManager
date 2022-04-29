package application.model;

import java.io.Serializable;

public class Website implements Serializable {
    private String username;
    private String websiteName;
    private Password password;

    public Website(String username, String websiteName, Password password) {
        this.username = username;
        this.websiteName = websiteName;
        this.password = password;
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

    @Override
    public String toString() {
        return "Website{" +
                "username='" + username + '\'' +
                ", websiteName='" + websiteName + '\'' +
                ", password=" + password +
                '}';
    }
}