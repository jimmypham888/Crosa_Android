package com.example.apple.croasa.callback;

import com.example.apple.croasa.model.Contact;

import java.util.ArrayList;

public interface Status_Call_CallBack {

    void loadListStatusCall_Success(ArrayList<Contact> arrayList);

    void loadListStatusCall_Fail(String status);
}
