package com.example.apple.croasa.callback;

import com.example.apple.croasa.model.Contact;
import com.example.apple.croasa.model.LoginObject;

public interface LoginCallBack {

    void logInSuccess(LoginObject contact);

    void logInFaile(String status);
}
