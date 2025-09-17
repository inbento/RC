package com.example.rc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.OnBackPressedCallback;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StatisticActivity extends AppCompatActivity {

    private Button btnBack;
    private TextView statsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        btnBack = findViewById(R.id.btnBack);
        statsText = findViewById(R.id.statsText);

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
        String statistics = "• ОБЩАЯ СТАТИСТИКА\n\n" +

                "• ПОБЕДЫ ПО ЦВЕТАМ\n" +
                "• Белые: 12 побед\n" +
                "• Черные: 8 побед\n\n" +

                "• СТАТИСТИКА КОРОЛЕЙ\n" +
                "• Король людей: 7 игр\n" +
                "• Король драконов: 5 игр\n" +
                "• ???: 3 игры\n" +
                "• ???: 5 игр\n\n" +

                "• СЪЕДЕННЫЕ ФИГУРЫ\n" +
                "• Всего: 147 фигур\n" +
                "• Пешки: 89\n" +
                "• Ладьи: 18\n" +
                "• Кони: 15\n" +
                "• Слоны: 12\n" +
                "• Ферзи: 10\n\n" +

                "• ВРЕМЯ В ИГРЕ\n" +
                "• Общее время: 45ч 32м\n" +
                "• Средняя игра: 25м\n" +
                "• Максимальная сессия: 3ч 15м\n\n" +

                "• ДОПОЛНИТЕЛЬНО\n" +
                "• Всего игр: 20\n" +
                "• Ничьих: 0\n" +
                "• Лучшая серия: 5 побед";

        statsText.setText(statistics);
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