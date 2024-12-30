package controllers;

import database.User;
import models.SearchModel;
import views.SearchView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SearchController {
    private final SearchModel model;
    private final SearchView view;
    private String searchQuery;

    public SearchController(SearchModel model, SearchView view, JTextField searchField) {
        this.model = model;
        this.view = view;
        this.searchQuery = searchQuery;

        searchField.addActionListener(e -> {
            String searchQuery = searchField.getText().trim();

            List<User> totalUsers = model.getUsersList();
            List<User> foundUsers = new ArrayList<>();

            for (User user : totalUsers) {
                if(user.getName().toLowerCase().contains(searchQuery.toLowerCase()) || user.getUsername().toLowerCase().contains(searchQuery.toLowerCase())) {
                    foundUsers.add(user);
                }
            }
            view.displayResults(foundUsers);
        });
    }
}
