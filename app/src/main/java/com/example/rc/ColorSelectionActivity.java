package com.example.rc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ColorSelectionActivity extends AppCompatActivity {

    private static final String TAG = "ColorSelectionActivity";
    private static final int REQUEST_TIME_SELECTION = 1;
    private int selectedTimeMinutes = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_selection);
        Log.d(TAG, "onCreate started");

        initViews();
        updateTimeDisplay();
        Log.d(TAG, "ColorSelectionActivity setup completed");
    }

    private void initViews() {
        Button btnBack = findViewById(R.id.btnBack);
        Button btnWhite = findViewById(R.id.btnWhite);
        Button btnBlack = findViewById(R.id.btnBlack);
        Button btnTimeSettings = findViewById(R.id.btnTimeSettings);

        // Кнопка назад - возврат в MainActivity
        btnBack.setOnClickListener(v -> {
            Log.d(TAG, "Back to MainActivity");
            finish();
        });

        // Кнопка настроек времени
        btnTimeSettings.setOnClickListener(v -> {
            Log.d(TAG, "Opening TimeSelection");
            openTimeSelection();
        });

        // Кнопки выбора цвета
        btnWhite.setOnClickListener(v -> {
            Log.d(TAG, "White color selected, starting game");
            startGame(true);
        });

        btnBlack.setOnClickListener(v -> {
            Log.d(TAG, "Black color selected, starting game");
            startGame(false);
        });
    }

    private void openTimeSelection() {
        Intent intent = new Intent(this, TimeSelectionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("current_time_minutes", selectedTimeMinutes);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_TIME_SELECTION);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_TIME_SELECTION && resultCode == RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bundle extras = data.getExtras();
                selectedTimeMinutes = extras.getInt("selected_time_minutes", 10);
                Log.d(TAG, "Time received from TimeSelection: " + selectedTimeMinutes);

                updateTimeDisplay();

                String timeDisplay = formatTimeForDisplay();
                Toast.makeText(this, "Время установлено: " + timeDisplay, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateTimeDisplay() {
        Button btnTimeSettings = findViewById(R.id.btnTimeSettings);
        if (btnTimeSettings != null) {
            String currentTime = formatTimeForDisplay();
            btnTimeSettings.setText("Время: " + currentTime);
            Log.d(TAG, "Time display updated: " + currentTime);
        }
    }

    private void startGame(boolean isWhite) {
        Log.d(TAG, "Starting game with color: " + (isWhite ? "white" : "black") +
                ", time: " + selectedTimeMinutes + " minutes");

        Intent intent = new Intent(this, ChessGameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("player_color_white", isWhite);
        bundle.putInt("game_time_minutes", selectedTimeMinutes);
        bundle.putInt("game_time_seconds", selectedTimeMinutes * 60);
        bundle.putBoolean("is_timed_game", selectedTimeMinutes > 0);
        bundle.putString("time_display", formatTimeForDisplay());
        intent.putExtras(bundle);

        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private String formatTimeForDisplay() {
        if (selectedTimeMinutes == 0) {
            return "Без ограничения";
        } else if (selectedTimeMinutes == 1) {
            return "1 минута";
        } else {
            return selectedTimeMinutes + " минут";
        }
    }

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
}