package com.example.apple.croasa.model;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apple.croasa.activity.Main_All_Activity;
import com.example.apple.croasa.callback.MainAllCallBack;
import com.example.apple.croasa.network.APIService;
import com.example.apple.croasa.network.ApiUltis;
import com.example.apple.croasa.network.RetrofitHelpDownload;
import com.example.apple.croasa.network.RetrofitHelper;
import com.example.apple.croasa.presenter.MainAllPresenter;
import com.example.apple.croasa.voip.IncomingCallActivity;
import com.example.apple.croasa.voip.Utils;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonObject;
import com.stringee.StringeeClient;
import com.stringee.call.StringeeCall;
import com.stringee.exception.StringeeError;
import com.stringee.listener.StringeeConnectionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAllModel {

    ArrayList<Contact> arrayList;
    APIService apiService = null;

    MainAllCallBack listener;



    public MainAllModel(MainAllCallBack listener) {
        this.listener = listener;
        apiService = RetrofitHelper.createService(APIService.class);
    }



    public void getListContacts(Map<String,String> userId) {

        apiService.getListContacts(userId).enqueue(new Callback<ContactsObject>() {
            @Override
            public void onResponse(Call<ContactsObject> call, Response<ContactsObject> response) {
                if (response.code()==200) {
                    arrayList = (ArrayList<Contact>) response.body().getData();
                    listener.loadListSuccess(arrayList);
                }
            }

            @Override
            public void onFailure(Call<ContactsObject> call, Throwable t) {
                listener.loadListFail(t.getMessage());
            }
        });
    }

}
