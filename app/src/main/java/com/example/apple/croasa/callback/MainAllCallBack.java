package com.example.apple.croasa.callback;

import com.example.apple.croasa.model.Contact;

import java.util.ArrayList;

public interface MainAllCallBack {

    public void loadListSuccess(ArrayList<Contact> contacts);

    public void loadListFail(String Exception);
}
