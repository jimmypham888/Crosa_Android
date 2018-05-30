package com.example.apple.croasa.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.croasa.R;
import com.example.apple.croasa.model.Contact;
import com.example.apple.croasa.view.Status_View;

import java.util.ArrayList;

public class Status_Call_Fragment extends Fragment implements Status_View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_list_contacts,container,false);
        return view;
    }

    @Override
    public void showListStatusCall_Success(ArrayList<Contact> arrayList) {

    }

    @Override
    public void showListStatusCall_Fail(String status) {

    }
}
