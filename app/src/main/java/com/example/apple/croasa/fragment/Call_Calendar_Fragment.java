package com.example.apple.croasa.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apple.croasa.R;
import com.example.apple.croasa.model.Contact;
import com.example.apple.croasa.view.Call_Calendar_View;

import java.util.ArrayList;

public class Call_Calendar_Fragment extends Fragment implements Call_Calendar_View {

    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_contacts,container,false);

        return view;
    }

    @Override
    public void showListCall(ArrayList<Contact> list) {

    }

    @Override
    public void showListCall_Error(String status) {

    }
}
