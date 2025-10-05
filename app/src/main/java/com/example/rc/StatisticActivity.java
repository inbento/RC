package com.example.rc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.OnBackPressedCallback;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class StatisticActivity extends AppCompatActivity {

    private Button btnBack;
    private TextView statsText, titleStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        btnBack = findViewById(R.id.btnBack);
        statsText = findViewById(R.id.statsText);
        titleStats = findViewById(R.id.titleStats);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackToMain();
            }
        });

        loadStatistics();
        setupBackPressedHandler();
    }

    private void loadStatistics() {
        // Собираем статистику из строковых ресурсов
        String statistics = "• " + getString(R.string.general_statistics) + "\n\n" +

                "• " + getString(R.string.wins_by_color) + "\n" +
                "• " + getString(R.string.white_wins) + "\n" +
                "• " + getString(R.string.black_wins) + "\n\n" +

                "• " + getString(R.string.kings_statistics) + "\n" +
                "• " + getString(R.string.human_king) + "\n" +
                "• " + getString(R.string.dragon_king) + "\n" +
                "• " + getString(R.string.elf_king) + "\n" +
                "• " + getString(R.string.gnom_king) + "\n\n" +

                "• " + getString(R.string.captured_pieces) + "\n" +
                "• " + getString(R.string.total_pieces) + "\n" +
                "• " + getString(R.string.pawns) + "\n" +
                "• " + getString(R.string.rooks) + "\n" +
                "• " + getString(R.string.knights) + "\n" +
                "• " + getString(R.string.bishops) + "\n" +
                "• " + getString(R.string.queens) + "\n\n" +

                "• " + getString(R.string.game_time) + "\n" +
                "• " + getString(R.string.total_time) + "\n" +
                "• " + getString(R.string.avg_game) + "\n" +
                "• " + getString(R.string.max_session) + "\n\n" +

                "• " + getString(R.string.additional) + "\n" +
                "• " + getString(R.string.total_games) + "\n" +
                "• " + getString(R.string.draws) + "\n" +
                "• " + getString(R.string.best_streak);

        statsText.setText(statistics);
    }

    // Для правильной загрузки языка
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

    private void setupBackPressedHandler() {
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                goBackToMain();
            }
        });
    }

    private void goBackToMain() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}