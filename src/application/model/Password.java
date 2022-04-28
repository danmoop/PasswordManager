package application.model;

public class Password {
    private String password;
    private long expirationDate;

    public Password(String password, long expirationDate) {
        this.password = password;
        this.expirationDate = expirationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Password{" +
                "password='" + password + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}