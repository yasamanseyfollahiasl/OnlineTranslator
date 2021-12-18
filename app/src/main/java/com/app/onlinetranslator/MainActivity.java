package com.app.onlinetranslator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    boolean isEn2Fa = true;

    ImageButton btnChangeLanguage;
    Button btnTranslate;
    TextView textViewFrom;
    ImageView imageViewFrom;
    TextView textViewTo;
    ImageView imageViewTo;
    EditText editText;

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
        editText = findViewById(R.id.editText);
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
            if (TextUtils.isEmpty(editText.getText().toString())) {
                Toast.makeText(MainActivity.this, "لطفا یک کلمه یا جمله برای ترجمه وارد کنید!", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(MainActivity.this, TranslateActivity.class);
                intent.putExtra("translate", editText.getText().toString());
                startActivity(intent);
            }
        });
    }
}
