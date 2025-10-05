package com.example.rc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ColorSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_selection);

        Button btnBack = findViewById(R.id.btnBack);
        Button btnWhite = findViewById(R.id.btnWhite);
        Button btnBlack = findViewById(R.id.btnBlack);
        TextView tvTitle = findViewById(R.id.tvTitle);

        btnBack.setOnClickListener(v -> finish());

        btnWhite.setOnClickListener(v -> startGame(true));
        btnBlack.setOnClickListener(v -> startGame(false));
    }

    // Добавьте для правильной работы языка
    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences preferences = newBase.getSharedPreferences("AppSettings", MODE_PRIVATE);
        String language = preferences.getString("language", "ru");

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = newBase.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);

        Context context = newBase.createConfigurationContext(configuration);
        super.attachBaseContext(context);
    }

    private void startGame(boolean isWhite) {
        Intent intent = new Intent(this, ChessGameActivity.class);
        intent.putExtra("player_color_white", isWhite);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}