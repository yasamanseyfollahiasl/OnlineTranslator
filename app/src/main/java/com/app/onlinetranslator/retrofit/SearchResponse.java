package com.app.onlinetranslator.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("response")
    public Status response;
    @SerializedName("debug")
    public Debug debug;
    @SerializedName("meta")
    public Meta meta;
    @SerializedName("data")
    public Data data;

    public static class Result {
        @SerializedName("id")
        public String id;
        @SerializedName("title")
        public String title;
        @SerializedName("title_en")
        public String title_en;
        @SerializedName("text")
        public String text;
        @SerializedName("source")
        public String source;
        @SerializedName("db")
        public String db;
        @SerializedName("num")
        public int num;
    }

    public static class Meta {
        String q;
        String type;
        String start;
    }

    public static class Debug {
        @SerializedName("message")
        public String message;
    }

    public static class Data {
        public int num_found;
        public List<Result> results;
    }

    public static class Status {
        public boolean status;
        public int code;
    }
}
