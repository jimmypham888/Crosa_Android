package com.example.apple.croasa.model;

import com.example.apple.croasa.callback.Status_Call_CallBack;
import com.example.apple.croasa.network.APIService;
import com.example.apple.croasa.network.RetrofitHelper;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Status_Call_Model {

    private Status_Call_CallBack listener;

    private APIService apiService;

    public Status_Call_Model(Status_Call_CallBack listener) {
        this.listener = listener;
        apiService = RetrofitHelper.createService(APIService.class);
    }

    public void loadStatusCall(Map<String,String> usr) {
        apiService.getListContacts(usr).enqueue(new Callback<ContactsObject>() {
            @Override
            public void onResponse(Call<ContactsObject> call, Response<ContactsObject> response) {
                listener.loadListStatusCall_Success((ArrayList<Contact>) response.body().getData());
            }

            @Override
            public void onFailure(Call<ContactsObject> call, Throwable t) {
                listener.loadListStatusCall_Fail(t.getLocalizedMessage());
            }
        });
    }

}
