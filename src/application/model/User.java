package application.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String email;
    private Password password;
    private List<Website> websites;
    private String securityQuestion;
    private String securityAnswer;
    private PasswordSettings passwordSettings;

    public User(String email, Password password, String securityQuestion, String securityAnswer, PasswordSettings passwordSettings) {
        this.email = email;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.passwordSettings = passwordSettings;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public List<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(List<Website> websites) {
        this.websites = websites;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public PasswordSettings getPasswordSettings() {
        return passwordSettings;
    }

    public void setPasswordSettings(PasswordSettings passwordSettings) {
        this.passwordSettings = passwordSettings;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password=" + password +
                ", websites=" + websites +
                ", securityQuestion='" + securityQuestion + '\'' +
                ", securityAnswer='" + securityAnswer + '\'' +
                ", passwordSettings=" + passwordSettings +
                '}';
    }
}