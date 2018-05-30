package com.example.apple.croasa.callback;

import com.example.apple.croasa.model.Contact;

import java.util.ArrayList;

public interface Call_Calendar_Callback {

    public void onLoadList_Success(ArrayList<Contact> arrayList);

    public void onLoadList_Fail(String status);
}
