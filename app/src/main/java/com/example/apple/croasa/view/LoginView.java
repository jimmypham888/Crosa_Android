package com.example.apple.croasa.view;

import com.example.apple.croasa.model.Contact;
import com.example.apple.croasa.model.LoginObject;

public interface LoginView {

    void logInSuccess(LoginObject contact);

    void logInFaile(String status);
}
