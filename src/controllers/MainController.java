package controllers;

import database.User;
import models.MainModel;
import views.MainView;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class MainController {

    private MainModel model;
    private MainView view;
    private User account;

    public MainController(User account, MainView view){
        this.model = new MainModel(account);
        this.view = view;
        this.account = account;

        view.setAccountMenuListener(new AccountMenuListener());
        view.setVisible(true);
    }

    private class AccountMenuListener implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {
            view.getCardLayout().show(view.getContentPanel(), "Account");
        }

        @Override
        public void menuDeselected(MenuEvent e) {}

        @Override
        public void menuCanceled(MenuEvent e) {}
    }
}
