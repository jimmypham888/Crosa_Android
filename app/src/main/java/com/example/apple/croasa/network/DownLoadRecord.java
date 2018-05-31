package com.example.apple.croasa.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Streaming;

public interface DownLoadRecord {
    @GET("recording/call-vn-1-9FSK70D94H-1527634465046")
    @Streaming
    Call<ResponseBody> downloadFile();
}
