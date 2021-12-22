package com.app.onlinetranslator;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.onlinetranslator.api.ApiClient;
import com.app.onlinetranslator.api.ApiInterface;
import com.app.onlinetranslator.api.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranslateActivity extends AppCompatActivity {
    Call<SearchResponse> translateResponseCall1 = null;
    Call<SearchResponse> translateResponseCall2 = null;
    Call<SearchResponse> translateResponseCall3 = null;
    Call<SearchResponse> translateResponseCall4 = null;
    TextView textViewTitle;
    ImageView imageViewBack;
    TextView textViewDaghigh;
    TextView textViewMani;
    TextView textViewMoshabeh;
    TextView textViewHamAva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        textViewTitle = findViewById(R.id.textViewTitle);
        imageViewBack = findViewById(R.id.imageViewBack);
        textViewDaghigh = findViewById(R.id.textViewDaghigh);
        textViewMani = findViewById(R.id.textViewMani);
        textViewMoshabeh = findViewById(R.id.textViewMoshabeh);
        textViewHamAva = findViewById(R.id.textViewHamAva);
        String translate = getIntent().getStringExtra("translate");
        textViewTitle.setText(translate);
        imageViewBack.setOnClickListener(view -> {
            finish();
        });


        if (!getIntent().hasExtra("translate") || TextUtils.isEmpty(getIntent().getStringExtra("translate"))) {
            Toast.makeText(this, "مقدار ورودی برای ترجمه نمی تواند خالی باشد", Toast.LENGTH_LONG).show();
            finish();
        }

        ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);


        //EXACT
        translateResponseCall1 = apiInterface.translate(
                "35192.FvObrLwvxBx2urUEhLG91uhzKjmUfGAiH6znVLer",
                getIntent().getStringExtra("translate"),
                "exact"
        );
        translateResponseCall1.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() == null ||
                        response.body().response == null ||
                        !response.body().response.status ||
                        response.body().data == null ||
                        response.body().data.results == null ||
                        response.body().data.results.size() == 0) {
                    Toast.makeText(TranslateActivity.this, "موردی یافت نشد.", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < response.body().data.results.size(); i++) {
                    stringBuilder.append(response.body().data.results.get(i).text);
                    stringBuilder.append("\n");
                }
                textViewDaghigh.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(TranslateActivity.this, "خطایی رخ داده است، دوباره تلاش کنید.", Toast.LENGTH_LONG).show();
            }
        });

        //AVA
        translateResponseCall2 = apiInterface.translate(
                "35192.FvObrLwvxBx2urUEhLG91uhzKjmUfGAiH6znVLer",
                getIntent().getStringExtra("translate"),
                "ava"
        );
        translateResponseCall2.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() == null ||
                        response.body().response == null ||
                        !response.body().response.status ||
                        response.body().data == null ||
                        response.body().data.results == null ||
                        response.body().data.results.size() == 0) {
                    Toast.makeText(TranslateActivity.this, "موردی یافت نشد.", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < response.body().data.results.size(); i++) {
                    stringBuilder.append(response.body().data.results.get(i).text);
                    stringBuilder.append("\n");
                }
                textViewHamAva.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(TranslateActivity.this, "خطایی رخ داده است، دوباره تلاش کنید.", Toast.LENGTH_LONG).show();
            }
        });

        //LIKE
        translateResponseCall3 = apiInterface.translate(
                "35192.FvObrLwvxBx2urUEhLG91uhzKjmUfGAiH6znVLer",
                getIntent().getStringExtra("translate"),
                "like"
        );
        translateResponseCall3.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() == null ||
                        response.body().response == null ||
                        !response.body().response.status ||
                        response.body().data == null ||
                        response.body().data.results == null ||
                        response.body().data.results.size() == 0) {
                    Toast.makeText(TranslateActivity.this, "موردی یافت نشد.", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < response.body().data.results.size(); i++) {
                    stringBuilder.append(response.body().data.results.get(i).text);
                    stringBuilder.append("\n");
                }
                textViewMoshabeh.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(TranslateActivity.this, "خطایی رخ داده است، دوباره تلاش کنید.", Toast.LENGTH_LONG).show();
            }
        });

        //TEXT
        translateResponseCall4 = apiInterface.translate(
                "35192.FvObrLwvxBx2urUEhLG91uhzKjmUfGAiH6znVLer",
                getIntent().getStringExtra("translate"),
                "text"
        );
        translateResponseCall4.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.body() == null ||
                        response.body().response == null ||
                        !response.body().response.status ||
                        response.body().data == null ||
                        response.body().data.results == null ||
                        response.body().data.results.size() == 0) {
                    Toast.makeText(TranslateActivity.this, "موردی یافت نشد.", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < response.body().data.results.size(); i++) {
                    stringBuilder.append(response.body().data.results.get(i).text);
                    stringBuilder.append("\n");
                }
                textViewMani.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Toast.makeText(TranslateActivity.this, "خطایی رخ داده است، دوباره تلاش کنید.", Toast.LENGTH_LONG).show();
            }
        });

    }
}