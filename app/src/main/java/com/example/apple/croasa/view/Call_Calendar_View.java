package com.example.apple.croasa.view;

import com.example.apple.croasa.model.Contact;

import java.util.ArrayList;

public interface Call_Calendar_View {

    public void showListCall(ArrayList<Contact> list);

    public void showListCall_Error(String status);

}
