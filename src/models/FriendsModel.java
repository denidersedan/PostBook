package models;

import database.DBConnection;
import database.User;

import java.util.List;

public class FriendsModel {
    private final User account;

    public FriendsModel(User account) {
        this.account = account;
    }

    public List<User> getFriendsList() {
        return DBConnection.getFriendsByUserId(account.getId());
    }

    public boolean addFriend(String friendUsername) {
        return DBConnection.insertFriendship(account.getId(), friendUsername);
    }
}
