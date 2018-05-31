package com.example.apple.croasa.network;

import com.example.apple.croasa.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelpDownload {

    private static final String TAG = RetrofitHelper.class.getSimpleName();
    private static final String BASE_URL = "https://api.stringee.com/v1/call/";

    public static <T> T createService(Class<T> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(provideOkHttpClient())
                .build();
        return retrofit.create(service);
    }

    public static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(provideOkHttpClient())
                .build();
    }


    private static Gson getGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    private static OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder().addHeader("X-STRINGEE-AUTH", "eyJjdHkiOiJzdHJpbmdlZS1hcGk7dj0xIiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJqdGkiOiJTS04yUGZBOFBKNnNucXJwMlRzVnV2ZHo0T2N4NDRiMzRPLTE1MjcxNDcxOTQiLCJpc3MiOiJTS04yUGZBOFBKNnNucXJwMlRzVnV2ZHo0T2N4NDRiMzRPIiwiZXhwIjoxNTI5NzM5MTk0LCJ1c2VySWQiOiJqaW1teSJ9.y6SQnGnBG0f7Z5p3aIMqkE_DGwRYxluSVz6vb1Smj4E")
                                .addHeader("Content-Type", "application/json").build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(logging)
                .build();
    }


    private static class NullOnEmptyConverterFactory extends Converter.Factory {
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                                Annotation[] annotations,
                                                                Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate =
                    retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody value) throws IOException {
                    if (value.contentLength() == 0) {
                        return null;
                    }
                    return delegate.convert(value);
                }
            };
        }
    }
}
