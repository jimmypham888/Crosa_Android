package com.example.apple.croasa.view;

import com.example.apple.croasa.model.Contact;

import java.util.ArrayList;

public interface Status_View {
    void showListStatusCall_Success(ArrayList<Contact> arrayList);

    void showListStatusCall_Fail(String status);
}
