package com.app.onlinetranslator.api;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context mContext) {
        if (retrofit == null) {

            ConnectivityInterceptor connectivityInterceptor = new ConnectivityInterceptor(mContext);

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(3, TimeUnit.MINUTES);
            httpClient.readTimeout(3, TimeUnit.MINUTES);
            httpClient.writeTimeout(3, TimeUnit.MINUTES);
            httpClient.addInterceptor(logging);
            httpClient.addInterceptor(connectivityInterceptor);

            OkHttpClient okHttpClient = httpClient.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.vajehyab.com/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}