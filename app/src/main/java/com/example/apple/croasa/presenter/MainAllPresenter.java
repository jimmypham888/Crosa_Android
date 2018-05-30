package com.example.apple.croasa.presenter;

import com.example.apple.croasa.callback.MainAllCallBack;
import com.example.apple.croasa.model.Contact;
import com.example.apple.croasa.model.MainAllModel;
import com.example.apple.croasa.view.MainAllView;

import java.util.ArrayList;
import java.util.Map;

public class MainAllPresenter implements MainAllCallBack {


    MainAllModel mainAllModel;


    MainAllView mainAllView;


    public MainAllPresenter(MainAllView mainAllView) {
        this.mainAllView = mainAllView;
        mainAllModel = new MainAllModel(this);
    }


    public void loadListContacts(Map<String,String> userId) {
        mainAllModel.getListContacts(userId);
    }

    @Override
    public void loadListSuccess(ArrayList<Contact> contacts) {

        mainAllView.onShowListContacts(contacts);

    }

    @Override
    public void loadListFail(String Exception) {

    }
}
