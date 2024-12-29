package controllers;

import database.User;
import models.AccountModel;
import models.MainModel;
import views.AccountView;
import views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {

    private MainModel model;
    private MainView view;
    private User account;

    public MainController(User account, MainView view){
        this.model = new MainModel(account);
        this.view = view;
        this.account = account;

        view.setAccountMenuActionListener(new AccountMenuActionListener());

        view.setVisible(true);
    }

    private class AccountMenuActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            AccountView accountView = new AccountView();
            AccountController accountController = new AccountController(account, accountView);
        }
    }
}
