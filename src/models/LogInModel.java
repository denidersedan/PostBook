package models;

import database.DBConnection;
import database.User;

import java.util.List;

public class LogInModel {

    public User checkUsernamePassword(String username, String password){
        List<User> users = DBConnection.getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
