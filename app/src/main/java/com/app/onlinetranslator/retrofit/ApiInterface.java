package com.app.onlinetranslator.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search")
    Call<SearchResponse> translate(
            @Query("token") String token,
            @Query("q") String q,
            @Query("type") String type
    );
}

