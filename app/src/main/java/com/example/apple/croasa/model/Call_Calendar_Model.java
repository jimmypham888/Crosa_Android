package com.example.apple.croasa.model;

import com.example.apple.croasa.callback.Call_Calendar_Callback;
import com.example.apple.croasa.network.APIService;
import com.example.apple.croasa.network.RetrofitClient;
import com.example.apple.croasa.network.RetrofitHelper;
import com.example.apple.croasa.view.MainAllView;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Call_Calendar_Model {

    private Call_Calendar_Callback listener;

    private APIService apiService;

    public Call_Calendar_Model(Call_Calendar_Callback listener) {
        this.listener = listener;
        apiService = RetrofitHelper.createService(APIService.class);
    }

    public void getListCall(Map<String, String> usr) {
        apiService.getListContacts(usr).enqueue(new Callback<ContactsObject>() {
            @Override
            public void onResponse(Call<ContactsObject> call, Response<ContactsObject> response) {
                listener.onLoadList_Success((ArrayList<Contact>) response.body().getData());
            }

            @Override
            public void onFailure(Call<ContactsObject> call, Throwable t) {

            }
        });
    }

}
