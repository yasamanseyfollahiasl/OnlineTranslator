package com.app.onlinetranslator.api;


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

    @GET("suggest")
    Call<SuggestResponse> suggest(
            @Query("token") String token,
            @Query("q") String q
    );
}

