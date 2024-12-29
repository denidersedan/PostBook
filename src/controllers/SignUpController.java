package controllers;

import database.DBConnection;
import database.User;
import models.MainModel;
import models.SignUpModel;
import views.MainView;
import views.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController {
    private SignUpModel model;
    private SignUpView view;

    public SignUpController(SignUpModel model, SignUpView view){
        this.model =model;
        this.view =view;
        this.view.setSignUpButtonActionListener(new SignUpListener());
        this.view.setVisible(true);
    }

    private class SignUpListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(model.checkExistingFullName(view.getFullName())) {
                if(model.checkExistingUsername(view.getUsername())){
                    if(model.checkExistingPassword(view.getPassword1()) || model.checkExistingPassword(view.getPassword2())){
                        if(model.checkSamePassword(view.getPassword1(), view.getPassword2())) {
                            DBConnection.insertUser(view.getUsername(), view.getPassword1(), view.getUsername());
                            User account = new User(view.getUsername(), view.getPassword1(), view.getFullName());

                            MainView mainView = new MainView(account);
                            MainController mainController = new MainController(account, mainView);
                            mainView.dispose();
                        } else{
                            view.setMessagePasswordVisible();
                        }
                    } else {
                        view.setMessageExistingPasswordVisible();
                    }
                } else{
                    view.setMessageUsernameVisible();
                }
            } else {
                view.setMessageFullNameVisible();
            }
        }
    }

}
