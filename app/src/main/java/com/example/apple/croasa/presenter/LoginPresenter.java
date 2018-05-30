package com.example.apple.croasa.presenter;

import com.example.apple.croasa.callback.LoginCallBack;
import com.example.apple.croasa.model.Contact;
import com.example.apple.croasa.model.LoginModel;
import com.example.apple.croasa.model.LoginObject;
import com.example.apple.croasa.view.LoginView;

import java.util.Map;

public class LoginPresenter implements LoginCallBack {

    private LoginView loginView;

    private LoginModel loginModel;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel(this);
    }

    public void login(Map<String, String> userId) {
        loginModel.login(userId);
    }

    @Override
    public void logInSuccess(LoginObject contact) {
        loginView.logInSuccess(contact);
    }

    @Override
    public void logInFaile(String status) {
        loginView.logInFaile(status);
    }
}
