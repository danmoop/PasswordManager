package application.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static DBConnection instance;
    private static Connection connection;
    private static Statement statement;
    private String path;

    private DBConnection(String path) {
        this.path = path;
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection("jdbc:sqlite:database.db");

            if (connection == null) {
                connection = DriverManager.getConnection(instance.path);
                statement = connection.createStatement();
                statement.executeUpdate("create table if not exists users (email string)");
            }
        }

        return instance;
    }

    public boolean isUserRegistered(String email) {
        return false;
    }
}