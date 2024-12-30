package models;

import database.DBConnection;
import database.Post;
import database.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeModel {
    private User user;

    public HomeModel(User user) {
        this.user = user;
    }

    public boolean addPost(String content) {
        return DBConnection.insertPost(user.getId(), content);
    }

    public List<Post> getPosts() {
        List<Post> posts = DBConnection.getFriendsPosts(user.getId());
        if(!posts.isEmpty()) {
            return posts;
        }
        return List.of();
    }
}
