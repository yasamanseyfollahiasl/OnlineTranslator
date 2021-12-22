package com.app.onlinetranslator;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.onlinetranslator.api.ApiClient;
import com.app.onlinetranslator.api.ApiInterface;
import com.app.onlinetranslator.api.SuggestResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    boolean isEn2Fa = true;

    ImageButton btnChangeLanguage;
    Button btnTranslate;
    TextView textViewFrom;
    ImageView imageViewFrom;
    TextView textViewTo;
    ImageView imageViewTo;
    AutoCompleteTextView autoCompleteTextView;

    private TextWatcher textWatcher;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnChangeLanguage = findViewById(R.id.btnChangeLanguage);
        btnTranslate = findViewById(R.id.btnTranslate);
        textViewFrom = findViewById(R.id.textViewFrom);
        imageViewFrom = findViewById(R.id.imageViewFrom);
        textViewTo = findViewById(R.id.textViewTo);
        imageViewTo = findViewById(R.id.imageViewTo);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(autoCompleteTextView.getText()) && autoCompleteTextView.getText().length() > 0) {
                    if (suggestionCall != null)
                        suggestionCall.cancel();
                    getSuggestion();
                }
            }
        };
        autoCompleteTextView.addTextChangedListener(textWatcher);
        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            if (TextUtils.isEmpty(autoCompleteTextView.getText().toString())) {
                Toast.makeText(MainActivity.this, "لطفا یک کلمه یا جمله برای ترجمه وارد کنید!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(MainActivity.this, TranslateActivity.class);
                intent.putExtra("translate", autoCompleteTextView.getText().toString());
                startActivity(intent);
            }
        });
        btnChangeLanguage.setOnClickListener(view -> {
            if (isEn2Fa) {
                isEn2Fa = false;
                textViewFrom.setText("English");
                imageViewFrom.setImageResource(R.drawable.flag_en);
                textViewTo.setText("Persian");
                imageViewTo.setImageResource(R.drawable.flag_fa);
            } else {
                isEn2Fa = true;
                textViewFrom.setText("Persian");
                imageViewFrom.setImageResource(R.drawable.flag_fa);
                textViewTo.setText("English");
                imageViewTo.setImageResource(R.drawable.flag_en);
            }
        });
        btnTranslate.setOnClickListener(view -> {
            if (TextUtils.isEmpty(autoCompleteTextView.getText().toString())) {
                Toast.makeText(MainActivity.this, "لطفا یک کلمه یا جمله برای ترجمه وارد کنید!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(MainActivity.this, TranslateActivity.class);
                intent.putExtra("translate", autoCompleteTextView.getText().toString());
                startActivity(intent);
            }
        });
    }

    private Call<SuggestResponse> suggestionCall;

    private void getSuggestion() {
        if (suggestionCall != null)
            suggestionCall.cancel();

        ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);

        suggestionCall = apiInterface.suggest(
                "35192.FvObrLwvxBx2urUEhLG91uhzKjmUfGAiH6znVLer",
                autoCompleteTextView.getText().toString()
        );

        suggestionCall.enqueue(new Callback<SuggestResponse>() {
            @Override
            public void onResponse(Call<SuggestResponse> call, Response<SuggestResponse> response) {
                if (response.body() == null ||
                        response.body().response == null ||
                        !response.body().response.status ||
                        response.body().data == null) {
                    return;
                }
                autoCompleteTextView.setAdapter(
                        new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, response.body().data.suggestion)
                );
                if (!autoCompleteTextView.isPopupShowing())
                    autoCompleteTextView.showDropDown();

            }

            @Override
            public void onFailure(Call<SuggestResponse> call, Throwable t) {

            }
        });
    }
}
