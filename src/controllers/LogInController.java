package controllers;

import database.DBConnection;
import database.User;
import models.LogInModel;
import models.MainModel;
import models.SignUpModel;
import views.LogInView;
import views.MainView;
import views.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInController {
    private LogInModel model;
    private LogInView view;

    public LogInController(LogInModel logInModel1, LogInView logInViewPage1){
        this.model =logInModel1;
        this.view = logInViewPage1;
        view.setSignUpButtonActionListener(new SignUpListener());
        view.setLogInButtonActionListener(new LogInListener());
        view.setVisible(true);
    }

    private class SignUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SignUpModel signUpModel = new SignUpModel();
            SignUpView signUpView = new SignUpView();
            SignUpController signUpController = new SignUpController(signUpModel, signUpView);
            view.dispose();
        }
    }

    private class LogInListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User account = model.checkUsernamePassword(view.getUsername(), view.getPassword());
            if(account != null){
                int userId = DBConnection.getUserIdByUsername(account.getUsername());
                if(userId != -1){
                    account.setId(userId);
                }
                MainView mainView = new MainView(account);
                MainController mainController = new MainController(account, mainView);
                view.dispose();
            }
            else{
                view.setMessageVisible(true);
            }
        }
    }
}
