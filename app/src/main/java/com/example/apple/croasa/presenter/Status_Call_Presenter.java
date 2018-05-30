package com.example.apple.croasa.presenter;

import com.example.apple.croasa.callback.Status_Call_CallBack;
import com.example.apple.croasa.model.Contact;
import com.example.apple.croasa.model.Status_Call_Model;
import com.example.apple.croasa.view.Status_View;

import java.util.ArrayList;
import java.util.Map;

public class Status_Call_Presenter implements Status_Call_CallBack {

    Status_Call_Model model;

    Status_View listener;

    public Status_Call_Presenter(Status_View listener) {
        this.listener = listener;
        model = new Status_Call_Model(this);
    }

    public void loadListStatusCall(Map<String,String> usr) {
        model.loadStatusCall(usr);
    }

    @Override
    public void loadListStatusCall_Success(ArrayList<Contact> arrayList) {
        listener.showListStatusCall_Success(arrayList);
    }

    @Override
    public void loadListStatusCall_Fail(String status) {
        listener.showListStatusCall_Fail(status);
    }
}
