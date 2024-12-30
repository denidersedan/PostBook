package models;

import database.DBConnection;
import database.User;

import java.util.List;

public class SearchModel {
    public List<User> getUsersList() {
        return DBConnection.getUsers();
    }
}
