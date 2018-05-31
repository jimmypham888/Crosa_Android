package com.example.apple.croasa.network;


import com.example.apple.croasa.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TienNM
 */
public class RetrofitHelper {

    private static final String TAG = RetrofitHelper.class.getSimpleName();
    private static final String BASE_URL = "http://45.124.94.45:9090/";
    private static final String BASE_URL1 = "https://api.stringee.com/";
//    private static final String BASE_URL = "https://www.androidhive.info/";

    public static <T> T createService(Class<T> sevices) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(provideOkHttpClient())
                .build();
        return retrofit.create(sevices);
    }
    public static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL1)
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
