package application.tools;

import application.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    public static DatabaseManager instance;

    private static String path;
    private List<User> users;

    private static User activeUser;

    private DatabaseManager(String path) throws IOException {
        DatabaseManager.path = path;
        this.users = new ArrayList<>();
    }

    public static DatabaseManager getInstance() throws IOException {
        if (instance == null) {
            instance = new DatabaseManager("database.bin");
        }

        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void add(User user) {
        users.add(user);
        save();
    }

    public User findByEmail(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return users.get(i);
            }
        }

        return null;
    }

    public static void setActiveUser(User activeUser) {
        DatabaseManager.activeUser = activeUser;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public void set(String email, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                users.set(i, user);
            }
        }

        save();
    }

    public void init() throws IOException {
        File file = new File(path);

        if (file.exists()) {
            load();
        } else {
            save();
        }
    }

    private void load() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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