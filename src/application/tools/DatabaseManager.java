package application.tools;

import application.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    // The class implements Singleton design pattern
    public static DatabaseManager instance;

    // Path variable is a path to a flat file containing the information
    private static String path;

    // The list of users works as a database, containing all registered users
    private List<User> users;

    // activeUser is a currently authenticated user, null before signing in
    private User activeUser;

    private DatabaseManager(String path) throws IOException {
        DatabaseManager.path = path;
        this.users = new ArrayList<>();
    }

    /**
     * @return the instance of a singleton database manager class
     */
    public static DatabaseManager getInstance() throws IOException {
        if (instance == null) {
            instance = new DatabaseManager("database.bin");
        }

        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setActiveUser(User user) {
        activeUser = user;
    }

    public User getActiveUser() {
        return activeUser;
    }

    /**
     * This function adds a user to a list of users
     * Save after user has been added
     *
     * @param user is a newly created user after signing up
     */
    public void add(User user) {
        users.add(user);
        save();
    }

    /**
     * This function finds a user in the database by an email
     *
     * @param email is a user's email
     * @return a user with specified email or null if user is not in the database
     */
    public User findByEmail(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return users.get(i);
            }
        }

        return null;
    }

    /**
     * This function replaces the user object with a new one after modifications have been made
     *
     * @param email is a user's email, which works as a key to find a user among others
     * @param user  is a new user object containing new information
     */
    public void set(String email, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                users.set(i, user);
            }
        }

        save();
    }

    // This function initializes the flat file and loads the information if it is present
    public void init() throws IOException {
        File file = new File(path);

        if (file.exists()) {
            load();
        } else {
            save();
        }
    }

    // The function loads the list of existing users from the flat file
    private void load() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // The function saves the list of users to a flat file
    public void save() {
        File file = new File(path);

        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(users);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}