package controllers;

import database.DBConnection;
import database.Post;
import database.User;
import models.HomeModel;
import views.HomeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class HomeController {
    private User user;
    private HomeModel model;
    private HomeView view;

    public HomeController(User account, HomeView view) {
        this.model = new HomeModel(account);
        this.view = view;
        this.user = account;

        displayPosts();
        view.setPostButtonActionListener(new PostButtonListener());
        view.setVisible(true);
    }

    private class PostButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String postContent = view.getPostText();

            if (postContent == null || postContent.trim().isEmpty()) {
                view.showPostDialog("Error", "Post content cannot be empty.", false);
                return;
            }

            String username = "CurrentUser";  // This should be replaced by the actual logged-in user's username

            // Call the model to create the post
            boolean isPostCreated = model.addPost(postContent);

            // Show dialog based on success/failure
            if (isPostCreated) {
                view.showPostDialog("Success", "Post created successfully.", true);
                view.clearPostText();  // Clear the text field after successful post
            } else {
                view.showPostDialog("Error", "Failed to create post.", false);
            }
        }
    }

    public void displayPosts() {
        view.clearPostsList();
        Map<Post, String> postsUser = new LinkedHashMap<>();
        List<Post> posts = model.getPosts();
        if(!posts.isEmpty()) {
            for (Post post : posts) {
                postsUser.put(post, post.getUser().getUsername());
            }
        }
        view.displayPosts(postsUser);
    }

}
