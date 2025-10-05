package com.example.rc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.OnBackPressedCallback;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnKings, btnStats, btnExit;
    private ImageButton btnLanguage;
    private String currentLanguage = "ru";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Загружаем язык ДО setContentView
        loadLanguagePreference();
        setLocale(currentLanguage);

        setContentView(R.layout.activity_main);

        initViews();
        setupButtonListeners();
        setupBackPressedHandler();
    }

    private void initViews() {
        btnLanguage = findViewById(R.id.btnLanguage);
        btnStart = findViewById(R.id.btnStart);
        btnKings = findViewById(R.id.btnKings);
        btnStats = findViewById(R.id.btnStats);
        btnExit = findViewById(R.id.btnExit);
    }

    private void loadLanguagePreference() {
        SharedPreferences preferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        currentLanguage = preferences.getString("language", "ru");
    }

    private void saveLanguagePreference(String languageCode) {
        getSharedPreferences("AppSettings", MODE_PRIVATE)
                .edit()
                .putString("language", languageCode)
                .apply();
    }

    private void setupBackPressedHandler() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                exitAppWithConfirmation();
            }
        });
    }

    private void setupButtonListeners() {
        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLanguageDialog();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        btnKings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKingsScreen();
            }
        });

        btnStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStatistics();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitAppWithConfirmation();
            }
        });
    }

    private void showLanguageDialog() {
        String[] languages = {
                getString(R.string.russian) + " (Русский)",
                getString(R.string.english) + " (English)"
        };

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle(R.string.select_language)
                .setItems(languages, (dialog, which) -> {
                    switch (which) {
                        case 0: // Русский
                            changeLanguage("ru");
                            break;
                        case 1: // Английский
                            changeLanguage("en");
                            break;
                    }
                })
                .show();
    }

    private void changeLanguage(String languageCode) {
        if (currentLanguage.equals(languageCode)) {
            Toast.makeText(this,
                    languageCode.equals("ru") ? "Уже выбран Русский" : "Already selected English",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        currentLanguage = languageCode;
        saveLanguagePreference(languageCode);
        setLocale(languageCode);

        recreate();
    }

    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    // Для правильной работы смены языка при запуске
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

    private void startGame() {
        Toast.makeText(this, getString(R.string.start_game), Toast.LENGTH_SHORT).show();
    }

    private void openKingsScreen() {
        Intent intent = new Intent(MainActivity.this, KingsActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void showStatistics() {
        Intent intent = new Intent(MainActivity.this, StatisticActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void exitAppWithConfirmation() {
        finishAffinity();
        System.exit(0);
    }
}