package controllers;

import database.User;
import models.MainModel;
import views.MainView;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.*;

public class MainController {

    private MainModel model;
    private MainView view;
    private User account;

    public MainController(User account, MainView view){
        this.model = new MainModel(account);
        this.view = view;
        this.account = account;

        view.setContentPanelMouseListener(new ContentPanelMouseAdapter());
        view.setHomeMenuListener(new HomeMenuListener());
        view.setAccountMenuListener(new AccountMenuListener());
        view.setFriendsMenuListener(new FriendsMenuListener());
        view.setSearchFieldActionListener(new SearchActionListener());
        view.setVisible(true);
    }

    private class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.getCardLayout().show(view.getContentPanel(), "Search");
        }
    }

    private class FriendsMenuListener implements MenuListener {
        @Override
        public void menuSelected(MenuEvent e) {
            view.getCardLayout().show(view.getContentPanel(), "Friends");
        }

        @Override
        public void menuDeselected(MenuEvent e) {}

        @Override
        public void menuCanceled(MenuEvent e) {}
    }

    private class ContentPanelMouseAdapter extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            view.getAccountMenu().setSelected(false);
            view.getHomeMenu().setSelected(false);
            view.getFriendsMenu().setSelected(false);
        }
    }

    private class HomeMenuListener implements MenuListener{
        @Override
        public void menuSelected(MenuEvent e) {
            view.getCardLayout().show(view.getContentPanel(), "Home");
        }

        @Override
        public void menuDeselected(MenuEvent e) {}

        @Override
        public void menuCanceled(MenuEvent e) {}
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
