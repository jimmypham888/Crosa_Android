package com.example.apple.croasa.presenter;

import com.example.apple.croasa.callback.Call_Calendar_Callback;
import com.example.apple.croasa.callback.Call_Note_Record_Callback;
import com.example.apple.croasa.model.Call_Calendar_Model;
import com.example.apple.croasa.model.Contact;
import com.example.apple.croasa.view.Call_Calendar_View;

import java.util.ArrayList;
import java.util.Map;

public class Call_Calendar_Presnter implements Call_Calendar_Callback{

    Call_Calendar_View listener;

    Call_Calendar_Model model;

    public Call_Calendar_Presnter(Call_Calendar_View listener) {
        this.listener = listener;
        model = new Call_Calendar_Model(this);
    }

    public void loadListCall(Map<String,String> usr) {
        model.getListCall(usr);
    }

    @Override
    public void onLoadList_Success(ArrayList<Contact> arrayList) {
        listener.showListCall(arrayList);
    }

    @Override
    public void onLoadList_Fail(String status) {
        listener.showListCall_Error(status);
    }
}
