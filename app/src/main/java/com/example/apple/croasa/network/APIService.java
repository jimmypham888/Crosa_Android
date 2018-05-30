package com.example.apple.croasa.network;

import com.example.apple.croasa.model.ContactsObject;
import com.example.apple.croasa.model.LoginData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface APIService {

    @Headers("Content-Type: application/json")
    @POST("/login/")
    Call<LoginData> login(@Body Map<String, String>  user);

    @Headers("Content-Type: application/json")
    @POST("getContact")
    Call<ContactsObject> getListContacts(@Body Map<String, String> userId);
}
