package com.example.apple.croasa.model;

import com.example.apple.croasa.callback.LoginCallBack;
import com.example.apple.croasa.network.APIService;
import com.example.apple.croasa.network.RetrofitHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel {

    APIService apiService;
    private LoginCallBack loginCallBack;

    public LoginModel(LoginCallBack loginCallBack) {
        this.loginCallBack = loginCallBack;
        apiService = RetrofitHelper.createService(APIService.class);
    }

    public void login(Map<String,String> userId) {

        apiService.login(userId).enqueue(new Callback<LoginData>() {
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                if (response.code() == 200) {
                    if (response.body().getStatus() == 1) {
                        loginCallBack.logInSuccess(response.body().getData());
                    } else {
                        loginCallBack.logInFaile("Wrong Account");
                    }
                } else {
                    loginCallBack.logInFaile("Connect Error");
                }
            }

            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                loginCallBack.logInFaile("Connect Error");
            }
        });
    }

}
