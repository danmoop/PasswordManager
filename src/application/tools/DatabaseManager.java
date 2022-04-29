package application.tools;

import application.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    public static DatabaseManager instance;

    private static String path;
    private List<User> users;

    private DatabaseManager(String path) throws IOException {
        DatabaseManager.path = path;
        this.users = new ArrayList<>();

        File file = new File(path);

        if (file.exists()) {
            this.users = load();
        } else {
            save();
        }
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

    private List<User> load() throws IOException {
        List<User> users = null;

        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return users;
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

    public void add(User user) {
        users.add(user);
        save();
    }
}