package com.example.apple.croasa.network;

import com.example.apple.croasa.model.ContactsObject;
import com.example.apple.croasa.model.HistoryResule;
import com.example.apple.croasa.model.LoginData;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface APIService {

    @Headers("Content-Type: application/json")
    @POST("/login/")
    Call<LoginData> login(@Body Map<String, String>  user);

    @Headers("Content-Type: application/json")
    @POST("getContact")
    Call<ContactsObject> getListContacts(@Body Map<String, String> userId);

    @GET("recording/call-vn-1-9FSK70D94H-1527634465046")
    @Streaming
    Call<ResponseBody> downloadFile();

    @GET
    @Streaming
    Call<ResponseBody> downloadFileRecord(@Url String url);

    @GET("/wp-content/uploads/2018/04/android-databinding-project-structure.png")
    Call<ResponseBody> getImage();

    @POST("/getAllHistoryCall")
    Call<HistoryResule> getAllHistory(@Body Map<String,String>  number);

}
